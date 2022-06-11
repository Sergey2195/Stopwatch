package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
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
        second = savedInstanceState?.getInt("seconds") ?: 0
        isRunning = savedInstanceState?.getBoolean("isRunning") ?: false
        flag = savedInstanceState?.getInt("flag") ?: 0
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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("seconds", second)
        outState.putBoolean("isRunning", isRunning)
        outState.putInt("flag", flag)
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