package com.example.myboundservice.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyBackroundService : Service() {
    private val TAG = "MyBackrountService"
    private var job: Job? = null
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var counter = 0
        val url = intent?.getStringExtra("Url")
        job = MainScope().launch {
            while (counter < 40) {
                counter++
                Log.d(TAG, "onStartCommand: ${counter}")
                delay(1000L)
            }
        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        Log.d(TAG, "onDestroy: ")
    }
}