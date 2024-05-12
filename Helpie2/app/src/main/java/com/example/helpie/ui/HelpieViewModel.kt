package com.example.helpie.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helpie.Localisation
import com.example.helpie.TripSummary
import com.example.helpie.UiState
import com.example.helpie.tripPlanificator.OjpSdk
import com.example.helpie.tripPlanificator.data.dto.response.TripDto
import com.example.helpie.tripPlanificator.extractTrip
import com.example.helpie.tripPlanificator.nextStep
import com.example.helpie.tripPlanificator.tripSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HelpieViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val tripFlow: Flow<TripDto?> = _uiState.map { it.trip }
    private val totalFlow: Flow<TripSummary?> = _uiState.map { it.summary }
    private val stepFlow: Flow<Int> = _uiState.map { it.currentStep }
    private val plannerFlow: Flow<OjpSdk> = _uiState.map { it.planner }
    private val targetFlow: Flow<Localisation> = _uiState.map { it.targetLocation }
    fun request() {
        Log.d("helpie","ojpSdk")
        viewModelScope.launch {
            val planner = plannerFlow.first() // Accessing the planner from the Flow

            val response = planner.tripRequest(targetFlow.first())

            val trip = extractTrip(response)
            Log.d("helpie","done !")
            _uiState.update {
                    currentState -> currentState.copy(trip = trip,
                currentStep = 0)
            }
        }
    }

   fun summary() {
       viewModelScope.launch {
           val sum = tripFlow.first()?.let { tripSummary(it) }

           if (sum != null) {
               Log.d("summary", "Duration: ${sum.duration}")
               Log.d("summary", "Start Time: ${sum.startTime}")
               Log.d("summary", "End Time: ${sum.endTime}")
               Log.d("summary", "Number of Steps: ${sum.npSteps}")
           }

           _uiState.update { currentState ->
               currentState.copy(summary = sum)
           }
       }
   }

    fun lauchNext() {
        viewModelScope.launch {
            if (stepFlow.first() < (totalFlow.first()?.npSteps ?: 0)) {
                val travel = tripFlow.first()?.let { nextStep(it, stepFlow.first()) }

                // Logging each element of StepInfo
                travel?.logValues()
                _uiState.update { currentState ->
                    currentState.copy(currentStep = stepFlow.first() + 1,
                        stepOngoing = travel)
                }
            }
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


}