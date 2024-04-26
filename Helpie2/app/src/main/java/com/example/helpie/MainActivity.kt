package com.example.helpie

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.example.helpie.ui.theme.AppTheme
import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.tripPlanificator.OjpSdk
import timber.log.Timber

class MainActivity : ComponentActivity() {
    companion object {
        private const val PERMISSION_REQUEST_CODE = 101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionsIfNecessary()

        OjpSdk(
            url = "https://api.opentransportdata.swiss/ojp2020",
            requesterReference = "Helpie",
            httpHeaders = hashMapOf(
                "Authorization" to "Bearer myAccessToken"
            )
        )

        setContent {
            AppTheme {
                HelpieApp()
            }
        }
    }

    private fun requestPermissionsIfNecessary() {
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
            && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)) {
            permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onStart() {
        super.onStart()
        requestPermissionsIfNecessary()
    }

    override fun onPause() {
        super.onPause()
        startForegroundService()
        Timber.tag("MainActivity").d("Foreground service started")
    }

    override fun onResume() {
        super.onResume()
        stopForegroundService()
        Timber.tag("MainActivity").d("Foreground service stopped")
    }

    private fun startForegroundService() {
        if (!isServiceRunning(ForegroundService::class.java)) {
            val startIntent = Intent(this, ForegroundService::class.java)
            startIntent.action = ForegroundService.Actions.START.toString()
            startService(startIntent)
        }
    }

    private fun stopForegroundService() {
        if (isServiceRunning(ForegroundService::class.java)) {
            val stopIntent = Intent(this, ForegroundService::class.java)
            stopIntent.action = ForegroundService.Actions.STOP.toString()
            startService(stopIntent)
        }
    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

}
