package com.example.nekofitness

data class ExerciseChoice (
    val exercisename:String,
    val exercisecategory:String,
    var isChecked:Boolean = false
)