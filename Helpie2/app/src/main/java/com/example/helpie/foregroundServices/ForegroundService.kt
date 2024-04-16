package com.example.helpie.foregroundServices

import android.app.PendingIntent
import android.app.Service
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
/*val pendingIntent = PendingIntent.getActivity(
   this,
   0,
   Intent(this, MainActivity::class.java),
   PendingIntent.FLAG_UPDATE_CURRENT
)*/

val notification = NotificationCompat.Builder(this, "running_channel")
   .setSmallIcon(R.drawable.ic_launcher_foreground)
   .setContentTitle("HELPIE")
   .setContentText("Voyage en cours")
   .setColor(0xFF0000FF.toInt())
   /*.setContentIntent(pendingIntent) // Set pending intent to launch activity
   /.addAction(
   /    android.R.drawable.ic_menu_revert, // Icon for the action button
       "Return to App", // Title for the action button
       pendingIntent // Pending intent to launch activity when the button is clicked
   )*/
   .build()
startForeground(1, notification)
}

enum class Actions {
START,STOP
}
}