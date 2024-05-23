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
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import androidx.core.graphics.red
import com.example.helpie.R

//import com.example.helpie.foregroundServices.OnSwipeTouchListener



class PunchNotification: Service(){

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            Actions.PUNCH.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private  fun start() {
        Log.d("PUNCH","start")
        // Create an Intent to navigate back to the last activity before closing the app
        val previousActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
        previousActivityIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, previousActivityIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = punchNotification(pendingIntent)
        startForeground(1, notification)

    }

    private fun punchNotification(pendingIntent: PendingIntent): Notification {
        Log.d("PUNCH","create notif")
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL) // Change to desired sound URI
        return NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("HELPIE")
            .setContentText("une étape est terminé !")
            .setColor(Color.parseColor("#FFA500"))
            .setColorized(true)
            .addAction(android.R.drawable.ic_media_previous, "Revenir au trajet", pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSound(soundUri) // Set the notification sound
            .setTimeoutAfter(3000) // Set timeout after 3 seconds
            .setOngoing(true)
            .setAutoCancel(false)
            .build()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.d("PUNCH","removed ?")
        super.onTaskRemoved(rootIntent)
        stopSelf() // Stop the service when the app's task is removed
    }


    enum class Actions {
                       STOP,PUNCH
    }
}