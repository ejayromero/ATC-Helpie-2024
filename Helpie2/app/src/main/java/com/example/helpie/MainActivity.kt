package com.example.helpie

import android.Manifest
import android.app.ActivityManager
import android.app.NotificationManager
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
import com.example.helpie.foregroundServices.ForegroundService.Companion.NOTIFICATION_ID_PUNCH
import com.example.helpie.foregroundServices.ForegroundService.Companion.NOTIFICATION_ID_TRAVEL
import com.example.helpie.ui.HelpieViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson


class MainActivity : ComponentActivity() {
    companion object {
        private const val PERMISSION_REQUEST_CODE = 101

    }

    private val sharedPrefs by lazy {
        getSharedPreferences("ui_state", Context.MODE_PRIVATE)
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
            val notif = viewModel.getNotification()
            if (notif != ForegroundService.Actions.None) {
                punchForegroundService(notif)
                // Schedule the stopForegroundService to run after 5 seconds
                handler.postDelayed(stopForegroundRunnable, 3000)
            }
            handler.postDelayed(this, updateIntervalMillis)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateLocation() {
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
        requestPermissionsIfNecessary()

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[HelpieViewModel::class.java]
        //get location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        handler.postDelayed(updateRunnable, updateIntervalMillis)
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        restoreUiState()
        requestPermissionsIfNecessary()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPause() {
        super.onPause()
        if (viewModel.haveATrip()) {
            startForegroundService()
            Log.d("MainActivity","Foreground service started")
        } else {
            Log.d("MainActivity","no foreground")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStop() {
        super.onStop()
        Log.d("uistate", "saving")
        saveUiState()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        stopForegroundService()
        Log.d("MainActivity","Foreground service stopped")
    }

    private fun startForegroundService() {
        requestPermissionsIfNecessary()
        val startIntent = Intent(this, ForegroundService::class.java)
        startIntent.action = ForegroundService.Actions.START.toString()
        Log.d("notif update", startIntent.action!!)
        startService(startIntent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun punchForegroundService(notif: ForegroundService.Actions) {
        val punchIntent = Intent(this, ForegroundService::class.java)
        punchIntent.action = notif.toString()
        Log.d("notif update", punchIntent.action!!)
        startService(punchIntent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val stopForegroundRunnable = Runnable {
        if (isServiceRunning(ForegroundService::class.java) ) {
            val stopIntent = Intent(this, ForegroundService::class.java)
            stopIntent.action = ForegroundService.Actions.STOP_NOTIFICATION.toString()
            startService(stopIntent)
        }
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

    private fun getDisplayedNotificationId(): Int? {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val activeNotifications = notificationManager.activeNotifications
        for (notification in activeNotifications) {
            when (notification.id) {
                NOTIFICATION_ID_TRAVEL -> return NOTIFICATION_ID_TRAVEL
                NOTIFICATION_ID_PUNCH -> return NOTIFICATION_ID_PUNCH
            }
        }
        return null
    }

    private fun hasWindowPermission(): Boolean {
        return Settings.canDrawOverlays(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveUiState() {
        val uiState = viewModel.getUIstate()
        with(sharedPrefs.edit()) {
            putString("phoneNumber", uiState.phoneNumber)
            putBoolean("usePhone", uiState.usePhone)

            putBoolean("debugging", uiState.debugging)

            putBoolean("easyRide", uiState.easyRide)

            putString("registeredLocation1", Gson().toJson(uiState.registeredLocation[0]))
            putString("registeredLocation2", Gson().toJson(uiState.registeredLocation[1]))
            putString("registeredLocation3", Gson().toJson(uiState.registeredLocation[2]))
            putString("registeredLocation4", Gson().toJson(uiState.registeredLocation[3]))

            apply()
        }
        Log.d("uistate", "uistate saved")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun restoreUiState() {

        val phoneNumber = sharedPrefs.getString("phoneNumber","")
        val usePhone = sharedPrefs.getBoolean("usePhone",false)

        val debugging = sharedPrefs.getBoolean("debugging",false)

        val easyRide = sharedPrefs.getBoolean("easyRide",true)

        val registeredLocationJson1 = sharedPrefs.getString("registeredLocation1", null)
        val registeredLocation1 = if (registeredLocationJson1 != null) Gson().fromJson(registeredLocationJson1, Localisation::class.java) else Localisation()

        val registeredLocationJson2 = sharedPrefs.getString("registeredLocation2", null)
        val registeredLocation2 = if (registeredLocationJson1 != null) Gson().fromJson(registeredLocationJson2, Localisation::class.java) else Localisation()

        val registeredLocationJson3 = sharedPrefs.getString("registeredLocation3", null)
        val registeredLocation3 = if (registeredLocationJson3 != null) Gson().fromJson(registeredLocationJson3, Localisation::class.java) else Localisation()

        val registeredLocationJson4 = sharedPrefs.getString("registeredLocation4", null)
        val registeredLocation4 = if (registeredLocationJson4 != null) Gson().fromJson(registeredLocationJson4, Localisation::class.java) else Localisation()

        val registeredLocation = listOf(registeredLocation1,registeredLocation2,registeredLocation3,registeredLocation4)

        val newState = phoneNumber?.let { UiState(it,usePhone = usePhone,debugging = debugging, easyRide = easyRide, registeredLocation = registeredLocation) }
        if (newState != null) {
            viewModel.restoreUI(newState)
        }
    }


}
