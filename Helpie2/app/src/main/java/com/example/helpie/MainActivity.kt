package com.example.helpie

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.example.helpie.ui.theme.AppTheme
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.helpie.foregroundServices.ForegroundService

class MainActivity : ComponentActivity() {
    companion object {
        private const val PERMISSION_REQUEST_CODE = 101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionsIfNecessary()


        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            0
        )*/
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
        Log.d("MainActivity", "Foreground service started")
    }

    override fun onResume() {
        super.onResume()
        stopForegroundService()
        Log.d("MainActivity", "Foreground service stopped")
    }

    private fun startForegroundService() {
        val startIntent = Intent(this, ForegroundService::class.java)
        startIntent.action = ForegroundService.Actions.START.toString()
        startService(startIntent)
    }

    private fun stopForegroundService() {
        val stopIntent = Intent(this, ForegroundService::class.java)
        stopIntent.action = ForegroundService.Actions.STOP.toString()
        startService(stopIntent)
    }

}
