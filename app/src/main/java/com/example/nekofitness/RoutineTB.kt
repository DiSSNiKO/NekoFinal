package com.example.nekofitness

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoutineTB (
    val name: String,
    val difficulty: String,
    val duration: String,
    val exercises: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)