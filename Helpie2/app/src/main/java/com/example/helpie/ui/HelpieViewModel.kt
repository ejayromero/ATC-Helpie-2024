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
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.langage.LocaleHelper
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
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * ViewModel class for managing the UI state and logic of the Helpie application.
 */
@RequiresApi(Build.VERSION_CODES.O)
class HelpieViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    /**
     * Restores the UI state. Memory management
     *
     * @param newState The UI state to restore.
     */
    fun restoreUI(newState: UiState) {
        _uiState.update { currentState -> currentState.copy(
            phoneNumber = newState.phoneNumber,
            usePhone = newState.usePhone,
            debugging = newState.debugging,
            easyRide = newState.easyRide,
            registeredLocation = newState.registeredLocation
        ) }
        Log.d("uistate", "uistate restored")
    }

    // Setter

    /**
     * Sets the flag indicating the UI needs to be cleaned up.
     */
    fun setClean() {
        _uiState.update { currentState -> currentState.copy(needClean = true) }
    }

    /**
     * Sets the flag indicating if the trip has finished.
     *
     * @param finish Boolean flag indicating if the trip has finished.
     */
    fun setFinish(finish : Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isFinish = finish)
        }
    }

    /**
     * Sets the flag indicating if a ticket is being used.
     *
     * @param isTicket Boolean flag indicating if a ticket is being used.
     */
    fun setTicket(isTicket: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(ticket = isTicket)
        }
    }

    /**
     * Sets the flag indicating if the app is waiting.
     *
     * @param wait Boolean flag indicating if the app is waiting.
     */
    fun setWait(wait: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(wait = wait)
        }
    }

    /**
     * Sets the flag indicating if the phone is being used.
     *
     * @param isUse Boolean flag indicating if the phone is being used.
     */
    fun setUsePhone(isUse: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(usePhone = isUse)
        }
    }

    /**
     * Sets the phone number.
     *
     * @param number The phone number.
     */
    fun setPhone(number: String) {
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = number)
        }
    }

    /**
     * Sets the language of the app.
     *
     * @param language The language of the app.
     */
    fun setLangage(langage : String) {
        _uiState.update { currentState -> currentState.copy(langage = langage,
            langageSwitch = true) }
    }

    /**
     * Sets the target location.
     *
     * @param target The target location.
     */
    fun setTarget(target: Localisation) {
        _uiState.update { currentState ->
            currentState.copy(targetLocation = target)
        }
    }

    /**
     * Sets the name of a registered location.
     *
     * @param index The index of the registered location.
     * @param name The name of the location.
     * @param registeredLocalisation List of registered locations.
     */
    fun setLocalisationName(index: Int, name: String, registeredLocalisation: List<Localisation>) {
        if (index in registeredLocalisation.indices) {
            val updatedLocalisation = registeredLocalisation.toMutableList()
            updatedLocalisation[index] = registeredLocalisation[index].copy(destinationName = name)
            _uiState.update { currentState ->
                currentState.copy(registeredLocation = updatedLocalisation)
            }
        }
    }

    /**
     * Sets the address of a registered location.
     *
     * @param index The index of the registered location.
     * @param address The address of the location.
     * @param context The application context.
     */
    fun setLocalisationAddress(
        index: Int,
        address: String,
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

    /**
     * Sets the remaining time for the current step.
     *
     * @param point The point in the trip.
     * @param step Information about the step.
     */
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

    /**
     * Launches the trip. Important for notification
     *
     * @param haveATrip Boolean indicating if there is an active trip.
     */
    fun lauchTrip(haveATrip: Boolean) {
        Log.d("boolean", "changed")
        _uiState.update { currentState ->
            currentState.copy(tripIsGoing = haveATrip)
        }
    }

    /**
     * Gets the current UI state.
     */
    // Getter
    fun getUIstate(): UiState {
        return _uiState.value
    }

    /**
     * Checks if there is an active trip.
     */
    fun haveATrip(): Boolean {
        return _uiState.value.tripIsGoing
    }

    /**
     * Gets the language switch status.
     */
    fun getlangswitch() : String {
        if (_uiState.value.langageSwitch) {
            _uiState.update { currentState -> currentState.copy(
                langageSwitch = false) }
            return _uiState.value.langage
        }
        return ""
    }

    /**
     * Gets the notification action.
     */
    fun getNotification(): ForegroundService.Actions {
        val sending = _uiState.value.type
        _uiState.update { currentState -> currentState.copy(type = ForegroundService.Actions.None) }
        return sending
    }


    /**
     * Cleans up the UI state.
     */
    fun clean() {
        Log.d("CLEAN", "cleaning")
        timerJob?.cancel()
        _uiState.update { currentState ->
            currentState.copy(
                skipper = 0,
                trip = null,
                summary = null,
                isFinish = false,
                steps = listOf(),
                wait = false,
                currentStep = -1,
                timeNeeded = "start",
                showDialog = false,
                targetLocation = Localisation(isValid = true),
                tripIsGoing = false,
            )
        }
    }

    /**
     * Increases the skipper count.
     */
    fun UpSkip() {
        _uiState.update { currentState -> currentState.copy(skipper = _uiState.value.skipper +1 ) }
    }

    /**
     * Toggles the debugging mode.
     */
    fun SwitchDebug() {
        _uiState.update {currentState -> currentState.copy(debugging = !_uiState.value.debugging)}
    }

    /**
     * Toggles the easy ride mode.
     */
    fun SwitchTicket() {
        _uiState.update {currentState -> currentState.copy(easyRide = !_uiState.value.easyRide)}
    }

    /**
     * Sends a notification.
     *
     * @param type The type of notification.
     */
    fun sendNotification(type : ForegroundService.Actions) {
        _uiState.update { currentState -> currentState.copy(type = type) }
    }

    /**
     * Checks if the user is close to a destination during a walk.
     */
    fun isClose() {
        if (_uiState.value.steps[_uiState.value.currentStep] is walkInfo) {
            val d1 = _uiState.value.currentLocation.latitude
            val D1 = (_uiState.value.steps[_uiState.value.currentStep] as walkInfo).endLatitude!!
            val d2  = _uiState.value.currentLocation.longitude
            val D2 = (_uiState.value.steps[_uiState.value.currentStep] as walkInfo).endLongitude!!
            val dist = 5
            if (haversine(d1,d2,D1,D2) < dist) {
                sendNotification(ForegroundService.Actions.WalkCloseStop)
            }
        }
    }

    private fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371e3 // Earth's radius in meters
        val phi1 = lat1.toRadians()
        val phi2 = lat2.toRadians()
        val dphi = (lat2 - lat1).toRadians()
        val dlambda = (lon2 - lon1).toRadians()

        val a = sin(dphi / 2).pow(2) + cos(phi1) * cos(phi2) * sin(dlambda / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }

    private fun Double.toRadians() = this * PI / 180


    /**
     * Checks if the UI needs to be cleaned.
     */
    fun needToClose() {
        if (_uiState.value.needClean) {
            Log.d("cleaning", "cleaning go")
            clean()
            _uiState.update { currentState -> currentState.copy(needClean = false) }
        }
    }

    /**
     * Sends a trip request and updates the UI accordingly.
     */
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

    /**
     * Launches the next step in the trip.
     *
     * @return The name of the next screen.
     */
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


    /**
     * Launches Google Maps for navigation.
     *
     * @param context The application context.
     */
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

    /**
     * Opens a link in the default browser.
     *
     * @param context The application context.
     * @param url The URL to open.
     */
    fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }


    /**
     * Toggles the dialog visibility.
     */
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


    /**
     * Starts updating the remaining time for the current step.
     */
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

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // Cancel the timer job when ViewModel is cleared
    }

    /**
     * Updates the current location.
     *
     * @param current The current location.
     */
    fun updateCurrentLocation(current: LatLng) {
        _uiState.update { currentState ->
            currentState.copy(currentLocation = current)
        }
    }

    /**
     * Asynchronously converts the provided address string into geographical coordinates (latitude and longitude).
     *
     * @param context The application context.
     * @param addressStr The address string to convert into geographical coordinates.
     * @return The [LatLng] object containing the geographical coordinates if the conversion is successful, or `null` otherwise.
     */
    private suspend fun getLocationFromAddress(context: Context, addressStr: String): LatLng? {
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


