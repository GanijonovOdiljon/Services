package com.example.myboundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myboundservice.Service.MyBackroundService
import com.example.myboundservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStop.setOnClickListener {
            stopService(Intent(this, MyBackroundService::class.java))
        }
        binding.btnStart.setOnClickListener {
            val url = " kfjdlfi"
            val intent = Intent(this, MyBackroundService::class.java)
            intent.putExtra("Url", url)
            startService(intent)
        }
    }
}