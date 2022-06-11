package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var second = 0
    private var isRunning = false
    private var flag = 0
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.startBtn.setOnClickListener{
            isRunning = true
            runTimer()
        }
        binding.pauseBtn.setOnClickListener{
            isRunning = false
            flag = 0
        }
        binding.stopBtn.setOnClickListener {
            isRunning = false
            flag = 0
            second = 0
            binding.currTime.text = "0:00:00"
        }
    }
    private fun runTimer() {
        if (flag == 0){
            flag = 1
            Thread {
                while (isRunning){
                    handler.post(runnable)
                    Thread.sleep(1000)
                }

            }.start()
        }
    }
    private val runnable = Runnable {
        var hours = second / 3600
        var minutes = (second % 3600) / 60
        var secs = second % 60

        var time = String.format("%d:%02d:%02d", hours, minutes, secs)
        binding.currTime.text = time
        if (isRunning) {
            second++
        }
    }
}