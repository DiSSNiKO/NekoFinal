package com.example.nekofitness

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nekofitness.databinding.ActivityMainBinding
import com.example.nekofitness.DataClasses.ArchiveFoodMacros
import com.example.nekofitness.broadCastRecievers.DateChangeReceiver
import java.util.Date


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dateChangeReceiver: DateChangeReceiver = DateChangeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver(dateChangeReceiver, IntentFilter(Intent.ACTION_DATE_CHANGED))

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(dateChangeReceiver)
    }
}