package com.example.nekofitness.DataClasses

import android.icu.text.CaseMap.Title

data class Exercises (
    val name: String,
    val type: String,
    val muscle: String,
    val equipment: String,
    val difficulty: String,
    val instructions: String

)