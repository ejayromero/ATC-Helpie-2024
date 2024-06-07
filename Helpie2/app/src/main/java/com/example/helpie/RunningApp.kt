package com.example.helpie

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

/**
 * Custom Application class to handle app-wide initializations. Handle foreground services.
 */
class RunningApp: Application() {

    /**
     * Called when the application is starting, before any other application objects have been created.
     * Create a notification channel.
     */
    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "running_channel",
                "Running Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )

            val notificationManager = getSystemService((Context.NOTIFICATION_SERVICE)) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}