package com.example.helpie.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helpie.HelpieScreen
import com.example.helpie.Localisation
import com.example.helpie.StepInfo
import com.example.helpie.UiState
import com.example.helpie.tripPlanificator.extractTrip
import com.example.helpie.tripPlanificator.nextStep
import com.example.helpie.tripPlanificator.tripSummary
import com.example.helpie.walkInfo
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.time.format.DateTimeParseException
import kotlin.math.max

@RequiresApi(Build.VERSION_CODES.O)
class HelpieViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun request() {
        Log.d("helpie","ojpSdk")
        viewModelScope.launch {
            val planner = _uiState.value.planner // Accessing the planner from the Flow

            val response = planner.tripRequest(_uiState.value.currentLocation,_uiState.value.targetLocation)

            val trip = extractTrip(response)
            Log.d("helpie","done !")
            _uiState.update {
                    currentState -> currentState.copy(trip = trip,
                currentStep = -1)
            }
            summary()
        }
    }

    fun summary() {

        _uiState.update { currentState ->
            currentState.copy(summary = null,
                steps = listOf())
        }

        val sum = _uiState.value.trip?.let { tripSummary(it) }
        var i = 0
        val steps = mutableListOf<StepInfo>()

        if (sum != null) {
            Log.d("summary", "Duration: ${sum.duration}")
            Log.d("summary", "Start Time: ${sum.startTime}")
            Log.d("summary", "End Time: ${sum.endTime}")
            Log.d("summary", "Number of Steps: ${sum.npSteps}")

            while (i < sum.npSteps) {
                val travel = _uiState.value.trip?.let { nextStep(it, i) }
                if (travel != null) {
                    steps.add(travel)
                }
                i += 1
            }
        }
        _uiState.update { currentState ->
            currentState.copy(summary = sum,
                steps = steps,
                wait = false)
        }
    }

    fun launchNext(): String {

        if (_uiState.value.currentStep < (_uiState.value.summary?.npSteps ?: 0)) {

            var next = _uiState.value.currentStep +1
            _uiState.update { currentState ->
                currentState.copy(currentStep = next)
            }
            return if (_uiState.value.steps[next] is  walkInfo) {
                Log.d("trip", "walk !")
                HelpieScreen.Walk.name
            } else {
                Log.d("trip", "transport !")
                HelpieScreen.WaitingTransport.name
            }

        }
        Log.d("trip", "done !")
        return HelpieScreen.Final.name


    }

    fun launchGoogleMaps(context: Context) {

        val dir = _uiState.value.steps[_uiState.value.currentStep] as walkInfo

        val destination = "${dir.endLatitude},${dir.endLongitude}"

        // Create a URI for the Google Maps app with satellite view
        val gmmIntentUri: Uri = Uri.parse("google.navigation:q=$destination&mode=w&t=s")

        // Create an Intent object
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

        // Set the package to Google Maps
        mapIntent.setPackage("com.google.android.apps.maps")

        context.startActivity(mapIntent)


        /*
        // Verify that Google Maps is installed
        if (mapIntent.resolveActivity(packageManager) != null) {
            // Start Google Maps
            startActivity(mapIntent)
        } else {
            Log.d("Google_Maps", "Google maps not installed")
            // Google Maps not installed, handle accordingly
            //Toast.makeText(this, "Google Maps app not installed", Toast.LENGTH_SHORT).show()
            //redirectToPlayStore()
        }*/
    }

    fun setTicket(isTicket: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(ticket = isTicket)
        }
    }

    fun setWait(wait: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(wait = wait)
        }
    }

    fun setUsePhone(isUse: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(usePhone = isUse)
        }
    }

    fun setPhone(number: String) {
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = number)
        }
    }

    fun switchEdit() {
        _uiState.update { currentState ->
            currentState.copy(editMode = !currentState.editMode)
        }
    }

    fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun setTarget(target: Localisation) {
        _uiState.update { currentState ->
            currentState.copy(targetLocation = target)
        }
        setTripOngoing()
    }

    fun setLocalisationName(index: Int, name: String, registeredLocalisation: List<Localisation>) {
        if (index in registeredLocalisation.indices) {
            val updatedLocalisation = registeredLocalisation.toMutableList()
            updatedLocalisation[index] = registeredLocalisation[index].copy(destinationName = name)
            _uiState.update { currentState ->
                currentState.copy(registeredLocation = updatedLocalisation)
            }
        }
    }

    fun setLocalisationAddress(index: Int, address: String, registeredLocalisation: List<Localisation>) {
        if (index in registeredLocalisation.indices) {
            val updatedLocalisation = registeredLocalisation.toMutableList()
            updatedLocalisation[index] = registeredLocalisation[index].copy(destinationAddress = address)
            _uiState.update { currentState ->
                currentState.copy(registeredLocation = updatedLocalisation)
            }
        }
    }

    fun switchDialog() {
        _uiState.update { currentState ->
            currentState.copy(showDialog = !currentState.showDialog)
        }
    }
    private var timerJob: Job? = null
    private var startTime: Instant? = null
    init {
        startUpdatingRemainingTime(done = false)
    }
   
    @RequiresApi(Build.VERSION_CODES.O)
    fun startUpdatingRemainingTime(done: Boolean) {
        timerJob?.cancel() // Cancel previous job if any

        timerJob = viewModelScope.launch {
            while (true) {
                delay(30000) // Update every 30 seconds
                if (uiState.value.steps.size > 0) {
                    uiState.value.steps[uiState.value.currentStep].let {
                        setRemainingTime(
                            it.calculateDuration(),
                            done
                        )
                    }
                }// Update remainingTime
            }
        }
    }
    fun setRemainingTime(time: String, done: Boolean = false) {
        if(done) {
            startTime = null
        }
        var remainingTimeInMinutes = 0
        if (startTime == null) {
            startTime = Clock.System.now() // Start the timer at the first call
        }
        val elapsedMinutes = (Clock.System.now().epochSeconds - startTime!!.epochSeconds) / 60
        remainingTimeInMinutes = max(0, time.toInt() - elapsedMinutes.toInt())
        _uiState.update { currentState -> currentState.copy(remainingTime = remainingTimeInMinutes)
        }
        Log.d("remainingTime1", "Remaining time: $remainingTimeInMinutes")
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // Cancel the timer job when ViewModel is cleared
    }
    
    fun setTripOngoing() {
        _uiState.update { currentState ->
            currentState.copy(tripOngoing = !currentState.tripOngoing)
        }
    }

    fun updateCurrentLocation(current: LatLng) {
        _uiState.update { currentState ->
            currentState.copy(currentLocation = current)
        }
        Log.d("LOCATION", "Location has been updated")
    }


}