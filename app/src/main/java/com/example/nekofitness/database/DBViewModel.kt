package com.example.nekofitness

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBViewModel(application: Application) : AndroidViewModel(application) {
    private val routineDao : RoutineTBDao
    private val exerciseDao : ExerciseTBDao
    val getExercises : LiveData<List<ExerciseTB>>
    val getRoutines : LiveData<List<RoutineTB>>

    init {
        routineDao = MainDatabase.getDatabase(application).routineDao()
        exerciseDao = MainDatabase.getDatabase(application).exerciseDao()
        getExercises = exerciseDao.getExercise()
        getRoutines = routineDao.getRoutines()
    }

    fun addRoutine(routineTB: RoutineTB){
        viewModelScope.launch(Dispatchers.IO){
            routineDao.insertRoutine(routineTB)
        }
    }
    fun addExercise(exerciseTB: ExerciseTB){
        viewModelScope.launch(Dispatchers.IO){
            exerciseDao.insertExercise(exerciseTB)
        }
    }
}