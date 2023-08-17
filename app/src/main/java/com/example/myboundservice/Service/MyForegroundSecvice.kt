package com.example.myboundservice.Service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myboundservice.R
import com.example.myboundservice.SecondActivity

class MyForegroundSecvice : Service() {
    private val TAG = "MyForgeundService"
    private var mediaPlayer: MediaPlayer? = null
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.musicmp3)
        mediaPlayer?.isLooping = false
        mediaPlayer?.setVolume(100f, 100f)

        createNotification()
        Log.d(TAG, "onCreate: ")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, SecondActivity::class.java)

        val pending =
            PendingIntent.getActivity(this, 100, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        val notification = Notification.Builder(this, "123")
            .setContentTitle("Music notification")
            .setContentText("Sevgim-aybim")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pending)
            .build()
        mediaPlayer?.start()
        startForeground(101, notification)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "123",
                "Sevgim-Aybim",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        Log.d(TAG, "onDestroy: ")
    }
}