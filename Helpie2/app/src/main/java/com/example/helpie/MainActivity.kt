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
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.foregroundServices.ForegroundService.Companion.NOTIFICATION_ID_PUNCH
import com.example.helpie.foregroundServices.ForegroundService.Companion.NOTIFICATION_ID_TRAVEL
import com.example.helpie.langage.LocaleHelper
import com.example.helpie.ui.HelpieViewModel
import com.example.helpie.ui.theme.AppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson



/**
 * Main activity of the Helpie application
 *
 * Control permission, foreground services and langage. Launch and create the application.
 */
class MainActivity : ComponentActivity() {

    /**
     * Permission code
     */
    companion object {
        private const val PERMISSION_REQUEST_CODE = 101

    }

    /**
     * Saving file
     */
    private val sharedPrefs by lazy {
        getSharedPreferences("ui_state", Context.MODE_PRIVATE)
    }


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location //access to location
    private lateinit var viewModel: HelpieViewModel //viewmodel copy

    private val updateIntervalMillis: Long = 500 //Update every 0.5 sec
    private val handler = Handler(Looper.getMainLooper())

    /**
     * Run
     *
     * handle update of the system
     */
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
            val langswitch = viewModel.getlangswitch()
            if (langswitch != "") {
                switchLangage(langswitch)
            }

            handler.postDelayed(this, updateIntervalMillis)
        }
    }

    /**
     * Updates the current location of the device.
     * Requests location permissions if not already granted.
     */
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

    /**
     * Called when the activity is starting. Initializes ViewModel, requests permissions, and starts the location update handler.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsIfNecessary()

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[HelpieViewModel::class.java]
        //get location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        handler.postDelayed(updateRunnable, updateIntervalMillis)

        val langswitch = viewModel.getlangswitch()
        if (langswitch != "") {
            switchLangage(langswitch)
        }

        setContent {
            AppTheme {
                HelpieApp()
            }
        }
    }

    /**
     * Requests necessary permissions from the user.
     */
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

    /**
     * Called when the activity is becoming visible to the user.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        restoreUiState()
        requestPermissionsIfNecessary()
    }

    /**
     * Called when the activity is no longer visible to the user.
     */
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

    /**
     * Called when the activity is no longer visible to the user.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStop() {
        super.onStop()
        Log.d("uistate", "saving")
        saveUiState()
    }

    /**
     * Called when the activity will start interacting with the user.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        stopForegroundService()
        Log.d("MainActivity","Foreground service stopped")
    }

    /**
     * Starts the foreground service. Overlay and persistant notification
     */
    private fun startForegroundService() {
        requestPermissionsIfNecessary()
        val startIntent = Intent(this, ForegroundService::class.java)
        startIntent.action = ForegroundService.Actions.START.toString()
        Log.d("notif update", startIntent.action!!)
        startService(startIntent)
    }

    /**
     * Sends a notification action to the foreground service. Short notification.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun punchForegroundService(notif: ForegroundService.Actions) {
        val punchIntent = Intent(this, ForegroundService::class.java)
        punchIntent.action = notif.toString()
        Log.d("notif update", punchIntent.action!!)
        startService(punchIntent)
    }

    /**
     * Runnable to stop the foreground service. (for short notification)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private val stopForegroundRunnable = Runnable {
        if (isServiceRunning(ForegroundService::class.java) ) {
            val stopIntent = Intent(this, ForegroundService::class.java)
            stopIntent.action = ForegroundService.Actions.STOP_NOTIFICATION.toString()
            startService(stopIntent)
        }
    }

    /**
     * Stops the foreground service.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun stopForegroundService() {
        if (isServiceRunning(ForegroundService::class.java)) {
            val stopIntent = Intent(this, ForegroundService::class.java)
            stopIntent.action = ForegroundService.Actions.STOP.toString()
            startService(stopIntent)
            viewModel.needToClose()
        }

    }

    /**
     * Checks if a specific service is running.
     *
     * @param serviceClass The service class to check.
     * @return `true` if the service is running, `false` otherwise.
     */
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    /**
     * Gets the ID of the currently displayed notification.
     *
     * @return The notification ID if found, `null` otherwise.
     */
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

    /**
     * Checks if the app has the permission to draw overlays.
     *
     * @return `true` if the app has the permission, `false` otherwise.
     */
    private fun hasWindowPermission(): Boolean {
        return Settings.canDrawOverlays(this)
    }

    /**
     * Saves the UI state to shared preferences.
     */
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

    /**
     * Restores the UI state from shared preferences.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun restoreUiState() {

        val phoneNumber = sharedPrefs.getString("phoneNumber","")
        val usePhone = sharedPrefs.getBoolean("usePhone",false)

        val debugging = sharedPrefs.getBoolean("debugging",false)

        val easyRide = sharedPrefs.getBoolean("easyRide",true)

        val registeredLocationJson1 = sharedPrefs.getString("registeredLocation1", null)
        val registeredLocation1 = if (registeredLocationJson1 != null) Gson().fromJson(registeredLocationJson1, Localisation::class.java) else Localisation(
            destinationName = "Maison",
            destinationAddress = "Rte de la Blécherette 1, 1052 Le Mont-sur-Lausanne",
            longitude =6.635555,
            latitude = 46.558945,
            isValid = true
        )

        val registeredLocationJson2 = sharedPrefs.getString("registeredLocation2", null)
        val registeredLocation2 = if (registeredLocationJson1 != null) Gson().fromJson(registeredLocationJson2, Localisation::class.java) else Localisation(
            destinationName = "Bel-Air",
            destinationAddress = "Bel-Air, Lausanne",
            longitude = 6.629292449529679,
            latitude = 46.52220677770554,
            isValid = true
        )

        val registeredLocationJson3 = sharedPrefs.getString("registeredLocation3", null)
        val registeredLocation3 = if (registeredLocationJson3 != null) Gson().fromJson(registeredLocationJson3, Localisation::class.java) else Localisation(
            destinationName = "Biotech",
            destinationAddress = "Chem. des Mines 9, 1202 Genève",
            longitude = 6.1482750577995295,
            latitude = 46.22212491537171,
            isValid = true
        )

        val registeredLocationJson4 = sharedPrefs.getString("registeredLocation4", null)
        val registeredLocation4 = if (registeredLocationJson4 != null) Gson().fromJson(registeredLocationJson4, Localisation::class.java) else Localisation(
            destinationName = "EPFL",
            destinationAddress = "Rte Cantonale, 1015 Lausanne",
            longitude =  6.566047222595748,
            latitude = 46.52219353016205,
            isValid = true

        )

        val registeredLocation = listOf(registeredLocation1,registeredLocation2,registeredLocation3,registeredLocation4)

        val newState = phoneNumber?.let { UiState(it,usePhone = usePhone,debugging = debugging, easyRide = easyRide, registeredLocation = registeredLocation) }
        if (newState != null) {
            viewModel.restoreUI(newState)
        }
    }

    /**
     * Switches the app language and recreates the activity to apply the new locale.
     *
     * @param langage The new language to switch to.
     */
    private fun switchLangage(langage : String) {
        Log.d("langage", "switch langage to $langage ")
        LocaleHelper.setLocale(this, langage)
        recreate()  // Recreate the activity to apply the new locale
    }


}
