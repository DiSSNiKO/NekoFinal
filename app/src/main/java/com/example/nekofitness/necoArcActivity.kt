package com.example.nekofitness

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.nekofitness.databinding.ActivityNecoArcBinding

class necoArcActivity : AppCompatActivity() {

    private lateinit var fullLayout : ConstraintLayout

private lateinit var binding: ActivityNecoArcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_neco_arc)
        fullLayout = findViewById(R.id.intialactivity)
        fullLayout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}