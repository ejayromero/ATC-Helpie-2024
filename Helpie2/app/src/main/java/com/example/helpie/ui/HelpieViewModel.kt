package com.example.helpie.ui

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
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
import com.example.helpie.tripPlanificator.data.dto.response.PlaceInfoDto
import com.example.helpie.tripPlanificator.extractLoca
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
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.io.IOException
import kotlin.math.max
import kotlin.time.Duration.Companion.hours

@RequiresApi(Build.VERSION_CODES.O)
class HelpieViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun clean() {
        Log.d("CLEAN", "cleaning")
        timerJob?.cancel()
        _uiState.update { currentState ->
            currentState.copy(
                skipper = 0,
                trip = null,
                summary = null,
                isFinish = false,
                tripOngoing = false,
                steps = listOf(),
                wait = false,
                currentStep = -1,
                timeNeeded = "start",
                showDialog = false,
                targetLocation = Localisation(isValid = true)
            )
        }
    }

    fun UpSkip() {
        _uiState.update { currentState -> currentState.copy(skipper = _uiState.value.skipper +1 ) }
    }

    fun SwitchDebug() {
        _uiState.update {currentState -> currentState.copy(debugging = !_uiState.value.debugging)}
    }
    fun sendNotification() {
        _uiState.update { currentState -> currentState.copy(BOUM = true) }
    }

    fun getNotification(): Boolean {
        if (_uiState.value.BOUM) {
            _uiState.update { currentState -> currentState.copy(BOUM = false) }
            return true
        }
        return false
    }

    fun request() {
        Log.d("helpie", "ojpSdk")
        viewModelScope.launch {
            val planner = _uiState.value.planner // Accessing the planner from the Flow

            val response = planner.tripRequest(_uiState.value.currentLocation,_uiState.value.targetLocation)

            val trip = extractTrip(response)

            val contextLoca = extractLoca(response)

            Log.d("helpie","done !")
            _uiState.update {
                    currentState -> currentState.copy(trip = trip,
                currentStep = -1)
            }
            summary(contextLoca)
        }
    }

    private fun summary(contextLoca : List<PlaceInfoDto>) {

        _uiState.update { currentState ->
            currentState.copy(
                summary = null,
                steps = listOf()
            )
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
                val travel = _uiState.value.trip?.let { nextStep(it, i, contextLoca) }

                if (travel != null) {
                    steps.add(travel)
                    travel.logValues()
                }
                i += 1
            }
        }
        _uiState.update { currentState ->
            currentState.copy(
                summary = sum,
                steps = steps,
                wait = false
            )
        }
    }

    fun launchNext(): String {
        if (_uiState.value.currentStep < (_uiState.value.summary?.npSteps ?: 0)) {
            _uiState.update { currentState ->
                currentState.copy(currentStep = _uiState.value.currentStep + 1)
            }
            val updatedState = _uiState.value
            return if (updatedState.steps[updatedState.currentStep] is walkInfo) {
                Log.d("trip", "walk !")
                HelpieScreen.Walk.name
            } else {
                Log.d("trip", "transport !")
                HelpieScreen.WaitingTransport.name
            }
        }

        Log.d("trip", "done !")
        setFinish(true)
        return HelpieScreen.StopTicket.name
    }

    fun setFinish(finish : Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isFinish = finish)
        }
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

    fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun setTarget(target: Localisation) {
        _uiState.update { currentState ->
            currentState.copy(targetLocation = target)
        }
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
//OLD
/*
    fun setLocalisationAddress(index: Int, address: String, registeredLocalisation: List<Localisation>, context: Context) {
        if (index in registeredLocalisation.indices) {
            val updatedLocalisation = registeredLocalisation.toMutableList()

            val geo = getLocationFromAddress(context = context, addressStr = address)
            if (geo != null) {
                updatedLocalisation[index] = registeredLocalisation[index].copy(destinationAddress = address, longitude = geo.longitude, latitude = geo.latitude)
            }

            _uiState.update { currentState ->
                currentState.copy(registeredLocation = updatedLocalisation)
            }

        }
    }*/


//NEW
    fun setLocalisationAddress(
    index: Int,
    address: String,
    context1: List<Localisation>,
    context: Context
) {
        if (index in _uiState.value.registeredLocation.indices) {
            viewModelScope.launch {
                val geo = getLocationFromAddress(context, address)
                val updatedLocalisation = _uiState.value.registeredLocation.toMutableList()
                if (geo != null) {
                    updatedLocalisation[index] = updatedLocalisation[index].copy(
                        destinationAddress = address,
                        longitude = geo.longitude,
                        latitude = geo.latitude,
                        isValid = true
                    )
                } else {
                    updatedLocalisation[index] = updatedLocalisation[index].copy(
                        destinationAddress = address,
                        isValid = false
                    )
                }
                _uiState.update { currentState ->
                    currentState.copy(registeredLocation = updatedLocalisation)
                }
                //_uiState.value = _uiState.value.copy(registeredLocation = updatedLocalisation)
            }
        }
    }


    fun switchDialog() {
        _uiState.update { currentState ->
            currentState.copy(showDialog = !currentState.showDialog)
        }
    }

    private var timerJob: Job? = null
    init {
        Log.d("init", "start initiating")
        viewModelScope.launch {
            uiState.collect { currentState ->
                if (currentState.steps.isNotEmpty() && currentState.currentStep >= 0) {
                    startUpdatingRemainingTime()
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun startUpdatingRemainingTime() {
        timerJob?.cancel() // Cancel previous job if any
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(30000) // Update every 30 seconds
                setRemainingTime(_uiState.value.timeNeeded, _uiState.value.steps[_uiState.value.currentStep])
            }
        }
    }

    fun setRemainingTime(point: String, step: StepInfo) {
        // Update the timeNeeded in the state
        _uiState.update { currentState ->
            currentState.copy(timeNeeded = point)
        }

        // Initialize remainingTimeInMinutes
        var remainingTimeInMinutes = 0

        Log.d("is callend", "is called")
        Log.d("skipper", _uiState.value.skipper.toString())

        // Log the point and call giveTime based on the point

        when (point) {
                "start", "end" -> {
                    Log.d("givetime $point", "is done")
                    val time = step.giveTime(point)
                    Log.d("givetime $point", time)

                    // Parse the time
                    val timeParsed = Instant.parse(time)
                    Log.d("givetime $point parsed", "$timeParsed")

                    // Get the current time
                    val startTime = Clock.System.now()
                    Log.d("givetime now", "$startTime")

                    // Calculate elapsed minutes
                    val elapsedMinutes = (timeParsed.epochSeconds - startTime.epochSeconds) / 60
                    remainingTimeInMinutes = max(0, elapsedMinutes.toInt() - _uiState.value.skipper)
                }

                else -> {
                    // Handle any other points if needed
                    Log.d("givetime", "Unknown point: $point")
                }
            }


        // Update the remaining time in the state
        _uiState.update { currentState ->
            currentState.copy(remainingTime = remainingTimeInMinutes)
        }
        Log.d("remainingTime1", "Remaining time: $remainingTimeInMinutes")
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // Cancel the timer job when ViewModel is cleared
    }

    fun setTripOngoing(Ongoing: Boolean) {
        Log.d("boolean", "changed")
        _uiState.update { currentState ->
            currentState.copy(tripOngoing = Ongoing)
        }
    }

    fun getTripOngoing(): Boolean {
        Log.d("boolean", _uiState.value.tripOngoing.toString())
        return _uiState.value.tripOngoing
    }


    fun updateCurrentLocation(current: LatLng) {
        _uiState.update { currentState ->
            currentState.copy(currentLocation = current)
        }
        Log.d("LOCATION", "Location has been updated")
    }

    suspend fun getLocationFromAddress(context: Context, addressStr: String): LatLng? {
        val geocoder = Geocoder(context)
        var latLng: LatLng? = null

        try {
            val addressList: List<Address>? = geocoder.getFromLocationName(addressStr, 1)
            if (!addressList.isNullOrEmpty()) {
                val address = addressList[0]
                latLng = LatLng(address.latitude, address.longitude)
            } else {
                Log.d("IS_NULL", "The found loc is null")
                throw IOException("Address not found")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("TEST_getName", "Error getting location from address", e)
        }

        return latLng
    }

}


