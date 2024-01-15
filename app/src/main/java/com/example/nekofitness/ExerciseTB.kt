package com.example.nekofitness

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseTB (
    val name: String,
    val category:String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)