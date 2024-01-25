package com.example.nekofitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.nekofitness.databinding.ActivityMainBinding
import com.example.nekofitness.fragments.AddRoutineFragment
import com.example.nekofitness.fragments.CalorieFragment
import com.example.nekofitness.fragments.NutritionFragment
import com.example.nekofitness.fragments.RoutineFragment
import com.example.nekofitness.fragments.StatsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}