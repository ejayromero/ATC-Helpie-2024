package com.example.helpie

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.helpie.tripPlanificator.di.dataSourceModule
import com.example.helpie.tripPlanificator.di.networkModule
import com.example.helpie.tripPlanificator.di.repositoryModule
import com.example.helpie.tripPlanificator.di.useCaseModule
import org.koin.core.context.startKoin

class RunningApp: Application() {

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

        startKoin {
            modules(listOf(networkModule, repositoryModule, dataSourceModule, useCaseModule))
        }
    }
}