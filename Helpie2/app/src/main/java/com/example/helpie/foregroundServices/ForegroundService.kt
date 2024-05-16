package com.example.helpie.foregroundServices

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.graphics.PixelFormat
import android.net.Uri
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.example.helpie.R
//import com.example.helpie.foregroundServices.OnSwipeTouchListener



class ForegroundService: Service(){

    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            Actions.START.toString() -> {
                start()
                showFloatingWindow()
            }
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private  fun start() {

        // Create an Intent to navigate back to the last activity before closing the app
        val previousActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
        previousActivityIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, previousActivityIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("HELPIE")
            .setContentText("Voyage en cours")
            .setColor(0xFF0000FF.toInt())
            .setColorized(true)
            .addAction(android.R.drawable.ic_media_previous, "Revenir au trajet", pendingIntent) // Add button
            .setPriority(NotificationCompat.PRIORITY_LOW) // Set low priority to keep it always visible
            .setOngoing(true) // Make the notification ongoing
            .setAutoCancel(false) // Ensure the notification is not automatically cancelled
            .setTimeoutAfter(Long.MAX_VALUE)
            .build()

        startForeground(1, notification)


    }
    private fun showFloatingWindow() {
        // Check if the permission is already granted for window overlay
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            // Permission not granted, request it
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }

        // Create WindowManager
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        // Inflate the layout for the floating window
        floatingView = LayoutInflater.from(this).inflate(R.layout.floating_window_layout, null)

        // Define the layout parameters for the floating window
        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
            },
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // Set gravity to top
        layoutParams.gravity = Gravity.TOP or Gravity.START

        // Add the view to the WindowManager
        windowManager.addView(floatingView, layoutParams)
        /*
        // Set a click listener to the floating window
        floatingView.setOnClickListener {
            // Create an Intent to launch the main activity
            val intent = Intent(this, com.example.helpie.MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                // Add any additional data or extras to the intent if needed
            }
            startActivity(intent)
        }

         */
        // Set a click listener to the floating window
        floatingView.setOnClickListener {
            // Create an Intent to launch the app
            val previousActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
            previousActivityIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val pendingIntent = PendingIntent.getActivity(this, 0, previousActivityIntent, PendingIntent.FLAG_IMMUTABLE)
            try {
                pendingIntent.send()
            } catch (e: PendingIntent.CanceledException) {
                e.printStackTrace()
            }
        }
        // Set a long click listener to the floating window to remove it
        floatingView.setOnLongClickListener {
            // Remove the floating window from the screen
            windowManager.removeView(floatingView)
            true // Indicate that the long click event has been consumed
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf() // Stop the service when the app's task is removed
    }


    override fun onDestroy() {
        super.onDestroy()
        // Remove the floating view when the service is destroyed but check if window exists first
        if (::windowManager.isInitialized && floatingView.isAttachedToWindow) {
            windowManager.removeView(floatingView)
        }
    }
    enum class Actions {
                       START,STOP
    }
}