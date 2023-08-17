package com.example.myboundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myboundservice.Service.MyForegroundSecvice
import com.example.myboundservice.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startService(Intent(this, MyForegroundSecvice::class.java))
            binding.TextView.text = "Service started"
        }
        binding.btnStop.setOnClickListener {
            stopService(Intent(this, MyForegroundSecvice::class.java))
            binding.TextView.text = "Service stopped"
        }
    }
}