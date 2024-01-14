package com.example.nekofitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.nekofitness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(RoutineFragment())
        binding.bottomnav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.newroutine -> replaceFragment(AddRoutineFragment())
                R.id.routines -> replaceFragment(RoutineFragment())
                R.id.addexercise -> replaceFragment(ExerciseAddFragment())
                R.id.stats -> replaceFragment(StatsFragment())
            else -> {}
            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment){
        val fragManager = supportFragmentManager
        val transaqshen = fragManager.beginTransaction()
        transaqshen.replace(R.id.fragCont, fragment)
        transaqshen.commit()
    }
}