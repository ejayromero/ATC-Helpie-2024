package com.example.helpie.foregroundServices

import android.app.PendingIntent
import android.app.Service
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.helpie.MainActivity
import com.example.helpie.R

class ForegroundService: Service(){

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private  fun start() {

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("HELPIE")
            .setContentText("Voyage en cours")
            .setColor(0xFF0000FF.toInt())
            .setColorized(true)
            .addAction(android.R.drawable.ic_media_previous, "Revenir au trajet", pendingIntent) // Add button
            .setPriority(NotificationCompat.PRIORITY_MIN) // Set low priority to keep it always visible
            .setOngoing(true) // Make the notification ongoing
            .setAutoCancel(false) // Ensure the notification is not automatically cancelled
            .setTimeoutAfter(Long.MAX_VALUE)
            .build()

        startForeground(1, notification)
    }

    enum class Actions {
                       START,STOP
    }
}