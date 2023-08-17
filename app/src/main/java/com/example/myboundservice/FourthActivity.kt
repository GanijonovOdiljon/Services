package com.example.myboundservice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myboundservice.Service.MyJobService
import com.example.myboundservice.databinding.ActivityFourthBinding

class FourthActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFourthBinding.inflate(layoutInflater) }
    private val TAG = "MyJobService"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStop.setOnClickListener {
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(100)
            Log.d(TAG, "onCreate: Job cancelled")
            Toast.makeText(this, "Job cancelled", Toast.LENGTH_SHORT).show()
        }
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
//            Toast.makeText(this, " Job Scheduled", Toast.LENGTH_SHORT).show()
//            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//            val componentName = ComponentName(this, MyJobService::class.java)
//            val jobInfo = JobInfo.Builder(100, componentName)
//                .setRequiresCharging(true)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .setPeriodic(15 * 60 * 1000)
//                .build()
//            val int = jobScheduler.schedule(jobInfo)
//            if (int == JobScheduler.RESULT_SUCCESS) {
//                Log.d(TAG, "onCreate: Job Scheduled")
//            } else {
//                Log.d(TAG, "onCreate: Job scheduling failed")
//            }
        }
    }
}