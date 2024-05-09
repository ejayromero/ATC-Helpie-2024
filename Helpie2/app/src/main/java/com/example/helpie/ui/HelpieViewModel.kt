package com.example.helpie.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helpie.Localisation
import com.example.helpie.UiState
import com.example.helpie.tripPlanificator.OjpSdk
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
    /*private val planificator = OjpSdk(
        baseUrl = "https://api.opentransportdata.swiss/",
        endpoint = "https://api.opentransportdata.swiss/ojp2020",
        requesterReference = "Helpie",
        token = "eyJvcmciOiI2NDA2NTFhNTIyZmEwNTAwMDEyOWJiZTEiLCJpZCI6IjAyZmIwZmM2OWQxMDRkNjY4NWNiZjQ0NWI1MjQyZjgxIiwiaCI6Im11cm11cjEyOCJ9"
    )*/

    val plannerFlow: Flow<OjpSdk> = _uiState.map { it.planner }
    val targetFlow: Flow<Localisation> = _uiState.map { it.targetLocation }
    fun request() {
        Log.d("helpie","ojpSdk")
        viewModelScope.launch {
            val planner = plannerFlow.first() // Accessing the planner from the Flow

            val response = planner.tripRequest(targetFlow.first())
            Log.d("helpie","done !")
            _uiState.update {
                    currentState -> currentState.copy(trip = response)
            }
        }
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

}