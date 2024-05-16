package com.example.helpie.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helpie.Localisation
import com.example.helpie.StepInfo
import com.example.helpie.UiState
import com.example.helpie.tripPlanificator.extractTrip
import com.example.helpie.tripPlanificator.nextStep
import com.example.helpie.tripPlanificator.tripSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HelpieViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    fun request() {
        Log.d("helpie","ojpSdk")
        viewModelScope.launch {
            val planner = _uiState.value.planner // Accessing the planner from the Flow

            val response = planner.tripRequest(_uiState.value.targetLocation)

            val trip = extractTrip(response)
            Log.d("helpie","done !")
            _uiState.update {
                    currentState -> currentState.copy(trip = trip,
                currentStep = 0)
            }
        }
    }

    fun summary() {
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
                steps = steps)
        }
    }

    fun lauchNext() {
        if (_uiState.value.currentStep < _uiState.value.steps.size) {
            // Logging each element of StepInfo
            _uiState.value.steps[_uiState.value.currentStep].logValues()

            _uiState.update { currentState ->
                currentState.copy(currentStep = _uiState.value.currentStep + 1)
            }

            Log.d("HelpieViewModel", "Type of stepOngoing: ${uiState.value.steps[_uiState.value.currentStep].javaClass.simpleName}")
            }
        Log.d("trip", "done !")
    }

    fun setTicket(isTicket: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(ticket = isTicket)
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

    fun setTripOngoing() {
        _uiState.update { currentState ->
            currentState.copy(tripOngoing = !currentState.tripOngoing)
        }
    }

}