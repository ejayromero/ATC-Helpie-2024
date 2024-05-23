package com.example.helpie.foregroundServices

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import androidx.core.graphics.red
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
                start("voyage en cours")
                showFloatingWindow()
            }
            Actions.PUNCH.toString() -> start("une étape est terminé !")
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private  fun start(contentText: String) {

        // Create an Intent to navigate back to the last activity before closing the app
        val previousActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
        previousActivityIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, previousActivityIntent, PendingIntent.FLAG_IMMUTABLE)



        if (contentText == "voyage en cours") {
            val color =  Color.parseColor("#0978c6")
            val notification = travelNotification(contentText, pendingIntent, color)
            startForeground(1, notification)
        }else {
            val color =  Color.parseColor("#FFA500")
            val notification = punchNotification(contentText, pendingIntent, color)
            startForeground(1, notification)
        }

        //vibrateDevice()

    }

    private fun punchNotification(contentText: String, pendingIntent: PendingIntent, color: Int): Notification {
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL) // Change to desired sound URI
        vibrateDevice()
        return NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("HELPIE")
            .setContentText(contentText)
            .setColor(color)
            .setColorized(true)
            .addAction(android.R.drawable.ic_media_previous, "Revenir au trajet", pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSound(soundUri) // Set the notification sound
            .setTimeoutAfter(3000) // Set timeout after 3 seconds
            .build()
    }
    private fun travelNotification(contentText: String, pendingIntent: PendingIntent, color: Int): Notification {
        vibrateDevice()
        return NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("HELPIE")
            .setContentText(contentText)
            .setColor(color)
            .setColorized(true)
            .addAction(android.R.drawable.ic_media_previous, "Revenir au trajet", pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .setAutoCancel(false)
            .build()
    }

    private fun vibrateDevice() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For newer versions of Android, you can use VibrationEffect
            val vibrationEffect = VibrationEffect.createOneShot(
                500, // The duration of the vibration in milliseconds
                VibrationEffect.DEFAULT_AMPLITUDE // The amplitude of the vibration. This can be any value from 1 to 255, or DEFAULT_AMPLITUDE
            )
            vibrator.vibrate(vibrationEffect)
        } else {
            // For older versions of Android, you can simply pass the vibration duration to the vibrate method
            vibrator.vibrate(500) // The duration of the vibration in milliseconds
        }
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
        layoutParams.gravity = Gravity.TOP or Gravity.RIGHT

        // Add the view to the WindowManager
        windowManager.addView(floatingView, layoutParams)

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
                       START,STOP,PUNCH
    }
}