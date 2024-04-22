package com.example.helpie.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helpie.UiState
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.network.fetchData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HelpieViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

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

    private fun request() {
        viewModelScope.launch {
            val responseData = fetchData()
            _uiState.update { currentState ->
                currentState.copy(request = responseData)
            }
        }
    }
    fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    // not needed anymore, started at start/close in main activity !

    /*
    fun startForegroundService(context: Context) {
        val intent = Intent(context, ForegroundService::class.java)
        intent.action = ForegroundService.Actions.START.toString()
        ContextCompat.startForegroundService(context, intent)
    }

    fun stopForegroundService(context: Context) {
        val intent = Intent(context, ForegroundService::class.java)
        intent.action = ForegroundService.Actions.STOP.toString()
        ContextCompat.startForegroundService(context, intent)
    }*/

}