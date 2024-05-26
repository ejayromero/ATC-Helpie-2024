package com.example.helpie

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.ui.theme.AppTheme
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.helpie.ui.HelpieViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng


class MainActivity : ComponentActivity() {
    companion object {
        private const val PERMISSION_REQUEST_CODE = 101

    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location
    private lateinit var viewModel: HelpieViewModel

    private val updateIntervalMillis: Long = 500 //Update every 0.5 sec
    private val handler = Handler(Looper.getMainLooper())
    private val updateRunnable = object: Runnable{
        @RequiresApi(Build.VERSION_CODES.O)
        override fun run() {
            updateLocation()
            if (viewModel.getNotification() != ForegroundService.Actions.None) {
                Log.d("is diff",viewModel.getNotification().toString())
                punchForegroundService()
                // Schedule the stopForegroundService to run after 5 seconds
                handler.postDelayed(stopForegroundRunnable, 3000)
            }
            handler.postDelayed(this, updateIntervalMillis)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val stopForegroundRunnable = Runnable {
        stopForegroundService()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateLocation() {
        //Log.d("Test_update","location_update")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
            return
        }


        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {
                currentLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                viewModel.updateCurrentLocation(currentLatLng)
            }

        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("main activity","try")
        requestPermissionsIfNecessary()

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[HelpieViewModel::class.java]
        //get location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        handler.postDelayed(updateRunnable, updateIntervalMillis)
        //à voir si il faut gérer le OnCleared? Unclear...
        setContent {
            AppTheme {
                HelpieApp()
            }
        }
    }

    private fun requestPermissionsIfNecessary() {
        val permissionsToRequest = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            !Settings.canDrawOverlays(this) &&
            !hasWindowPermission()
        ) {
            // Add window permission to the list of permissions to request
            permissionsToRequest.add(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        }

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPause() {
        super.onPause()
        if (viewModel.getTripOngoing()) {
            startForegroundService()
            Log.d("MainActivity","Foreground service started")
        } else {
            Log.d("MainActivity","no foreground")
        }
        //startForegroundService()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        stopForegroundService()
        Log.d("MainActivity","Foreground service stopped")
    }

    private fun startForegroundService() {
        val startIntent = Intent(this, ForegroundService::class.java)
        startIntent.action = ForegroundService.Actions.START.toString()
        startService(startIntent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun punchForegroundService() {
        Log.d("MainActivity","punch")
        val punchIntent = Intent(this, ForegroundService::class.java)
        punchIntent.action = viewModel.getNotification().toString()
        Log.d("notif update", punchIntent.action!!)
        startService(punchIntent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun stopForegroundService() {
        if (isServiceRunning(ForegroundService::class.java)) {
            val stopIntent = Intent(this, ForegroundService::class.java)
            stopIntent.action = ForegroundService.Actions.STOP.toString()
            startService(stopIntent)
            viewModel.needToClose()
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

    private fun hasWindowPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this)
        }
        return true // For pre-Marshmallow devices, assume permission is granted
    }


}
