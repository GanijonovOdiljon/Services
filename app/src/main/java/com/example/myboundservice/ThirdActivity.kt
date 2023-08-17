package com.example.myboundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.myboundservice.Service.MyBoundService
import com.example.myboundservice.databinding.ActivityThirdBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {
    private lateinit var myBoundService: MyBoundService
    private var isBound = false
    private val binding by lazy { ActivityThirdBinding.inflate(layoutInflater) }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                myBoundService.gettProgress().collect {
                    binding.textView.text = it.toString()
                    binding.prograessBar.progress = it

                    if (it == 100) {
                        binding.textView.text = "Finished"
                        binding.prograessBar.progress = 1
                    }
                }
            }
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val service = p1 as MyBoundService.MyBinder
            myBoundService = service.getMyBoundService()
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MyBoundService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}