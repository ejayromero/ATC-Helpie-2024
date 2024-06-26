package com.example.helpie.foregroundServices

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.helpie.R

/**
 * A foreground service responsible for managing notifications and a floating window.
 */
class ForegroundService() : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View

    companion object {
        const val NOTIFICATION_ID_TRAVEL = 1
        const val NOTIFICATION_ID_PUNCH = 2
    }

    /**
     * Enumeration for service actions.
     */
    enum class Actions {
        START, STOP, WalkCloseStop,Monter,Descendre,None,STOP_NOTIFICATION
    }

    private var travelcolor : Int = Color.parseColor("#0978c6")
    private var notifcolor : Int = Color.parseColor("#FFA500")

    /**
     * Binds the service to the given intent.
     */
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * Handles the start command for the service.
     *
     * @param intent The intent containing the action to be performed.
     * @param flags Additional data about this start request.
     * @param startId A unique identifier for the start request.
     * @return The service type code.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> {
                start(Actions.START)
                showFloatingWindow()
            }
            Actions.WalkCloseStop.toString() -> start(Actions.WalkCloseStop)
            Actions.Monter.toString() -> start(Actions.Monter)
            Actions.Descendre.toString() -> start(Actions.Descendre)
            Actions.STOP.toString() -> stopSelf()
            Actions.STOP_NOTIFICATION.toString() -> stopNotification(NOTIFICATION_ID_PUNCH)
            else -> start(Actions.None)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * Stops the specified notification.
     *
     * @param notificationId The ID of the notification to be stopped.
     */
    private fun stopNotification(notificationId: Int) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(notificationId)
    }

    /**
     * Starts the service with the given action type.
     *
     * @param type The action type to be performed.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun start(type: Actions) {

        // Create an Intent to navigate back to the last activity before closing the app
        val previousActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
        previousActivityIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)


        if (type == Actions.START) {

            val pendingIntent =
                PendingIntent.getActivity(this, 0, previousActivityIntent, PendingIntent.FLAG_IMMUTABLE)

            val notification = travelNotification(pendingIntent, travelcolor)
            startForeground(NOTIFICATION_ID_TRAVEL, notification)
        } else {

            val pendingIntent =
                PendingIntent.getActivity(this, 0, previousActivityIntent, PendingIntent.FLAG_IMMUTABLE)

            val notification = punchNotification(pendingIntent, type, notifcolor)
            //updateNotification()
            startForeground(NOTIFICATION_ID_PUNCH, notification)
        }


        }

    /**
     * Updates the existing notification.
     */
    private fun updateNotification() {
        val previousActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
        previousActivityIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, previousActivityIntent, PendingIntent.FLAG_IMMUTABLE)

        val updatedNotification = travelNotification(pendingIntent, notifcolor)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID_TRAVEL, updatedNotification)
    }

    /**
     * Creates a travel notification.
     *
     * @param pendingIntent The PendingIntent to be triggered on notification action.
     * @param color The color of the notification.
     * @return The created travel notification.
     */
        private fun travelNotification(pendingIntent: PendingIntent, color: Int): Notification {
            vibrateDevice()
            return NotificationCompat.Builder(this, "running_channel")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(getString(R.string.helpie))
                .setContentText(getString(R.string.voyage_en_cours))
                .setColor(color)
                .setColorized(true)
                .addAction(android.R.drawable.ic_media_previous,
                    getString(R.string.revenir_au_trajet), pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setAutoCancel(false)
                .setSound(null)
                .build()
        }

    /**
     * Creates a punch notification.
     *
     * @param pendingIntent The PendingIntent to be triggered on notification action.
     * @param type The type of punch action.
     * @param color The color of the notification.
     * @return The created punch notification.
     */
        @RequiresApi(Build.VERSION_CODES.O)
        private fun punchNotification(pendingIntent: PendingIntent, type: Actions, color: Int): Notification {
            Log.d("PUNCH", "create notif")

            var text = getString(R.string.une_tape_est_termin)
            when (type) {
                Actions.WalkCloseStop -> {
                    text = getString(R.string.tu_es_arriv_l_arr_t)
                    // Do something for WalkCloseStop
                    println("Handling WalkCloseStop")
                }
                Actions.Monter -> {
                    text = getString(R.string.le_transport_arrive_bient_t)
                    // Do something for TransportEvent
                    println("Handling TransportEvent")
                }
                Actions.Descendre -> {
                    text = getString(R.string.tu_dois_bient_t_descendre)
                    // Do something for TransportEvent
                    println("Handling TransportEvent")
                }
                else -> {
                    // Do something for None
                    println("Handling None")
                }
            }

            val soundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL) // Change to desired sound URI

            return NotificationCompat.Builder(this, "running_channel")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(getString(R.string.helpie))
                .setContentText(text)
                .setColor(color)
                .setColorized(true)
                .addAction(android.R.drawable.ic_media_previous, getString(R.string.revenir_au_trajet), pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSound(soundUri) // Set the notification sound
                .setTimeoutAfter(3000) // Set timeout after 3 seconds
                .build()
        }


    /**
     * Make the device vibrate
     */
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

    /**
     * Shows the floating window on the screen.
     *
     * @param windowManager The WindowManager instance.
     * @param floatingView The View to be displayed as the floating window.
     */
        @SuppressLint("RtlHardcoded")
        private fun showFloatingWindow() {
            // Check if the permission is already granted for window overlay
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                // Permission not granted, request it
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
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
            layoutParams.gravity = Gravity.TOP

            // Add the view to the WindowManager
            windowManager.addView(floatingView, layoutParams)

            // Get references to the draggable circle and main layout
            val draggableCircle: View = floatingView.findViewById(R.id.draggableCircle)
            val mainLayout: View = floatingView.findViewById(R.id.floatingWindowLayout)


            // Set a click listener to the floating window
            mainLayout.setOnClickListener {
                // Create an Intent to launch the app
                val previousActivityIntent = packageManager.getLaunchIntentForPackage(packageName)
                previousActivityIntent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                val pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    previousActivityIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
                try {
                    pendingIntent.send()
                } catch (e: PendingIntent.CanceledException) {
                    e.printStackTrace()
                }
            }

            // Handle touch events to allow dragging
            draggableCircle.setOnTouchListener(object : View.OnTouchListener {

                private var initialX = 0
                private var initialTouchX = 0f
                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    Log.d("window", "touch")
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            Log.d("window", "first")
                            // Remember the initial position
                            initialX = layoutParams.x
                            initialTouchX = event.rawX
                            Log.d("FloatingWindow", "ACTION_DOWN: initialX=$initialX, initialTouchX=$initialTouchX")
                            return true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            Log.d("window", "move")
                            // Calculate the X coordinate for the new position
                            layoutParams.x = initialX + (event.rawX - initialTouchX).toInt()
                            Log.d("FloatingWindow", "ACTION_MOVE: newX=${layoutParams.x}")
                            // Update the window position
                            // Use a Handler to ensure the update is run on the UI thread
                            val handler = Handler(Looper.getMainLooper())
                            handler.post {
                                windowManager.updateViewLayout(floatingView, layoutParams)
                                floatingView.requestLayout()
                            }
                            return true
                        }
                    }
                    return false
                }
            })
        }

    /**
     * Called when the app's task is removed.
     *
     * @param rootIntent The root intent of the app.
     */
        override fun onTaskRemoved(rootIntent: Intent?) {
            super.onTaskRemoved(rootIntent)
            stopSelf() // Stop the service when the app's task is removed
        }

    /**
    * Called when the service is destroyed.
    */
        override fun onDestroy() {
            super.onDestroy()
            // Remove the floating view when the service is destroyed but check if window exists first
            if (::windowManager.isInitialized && floatingView.isAttachedToWindow) {
                windowManager.removeView(floatingView)
            }
        }

    }
