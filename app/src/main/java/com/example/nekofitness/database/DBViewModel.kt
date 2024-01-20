package com.example.nekofitness.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBViewModel(application: Application) : AndroidViewModel(application) {
    private val routineDao : RoutineTBDao
    val getRoutines : LiveData<List<RoutineTB>>

    init {
        routineDao = MainDatabase.getDatabase(application).routineDao()
        getRoutines = routineDao.getRoutines()
    }

    fun addRoutine(routineTB: RoutineTB){
        viewModelScope.launch(Dispatchers.IO){
            routineDao.insertRoutine(routineTB)
        }
    }

}