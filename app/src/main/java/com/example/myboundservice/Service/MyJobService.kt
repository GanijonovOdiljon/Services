package com.example.myboundservice.Service

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyJobService : JobService() {

    private var counter = 0
    private val TAG = "MyJobService"
    private var job: Job? = null

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob: ")
        job = MainScope().launch {
            while (counter < 100) {
                delay(1000L)
                counter++
                Log.d(TAG, "onStartJob: ${counter}")
            }
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        job?.cancel()
        Log.d(TAG, "onStopJob: ")
        return true
    }
}