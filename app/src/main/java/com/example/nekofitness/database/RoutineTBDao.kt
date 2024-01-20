package com.example.nekofitness

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RoutineTBDao {
    @Upsert
    suspend fun insertRoutine(routineTB: RoutineTB)
    @Delete
    suspend fun deleteRoutine(routineTB: RoutineTB)
    @Query("SELECT * FROM ROUTINETB")
    fun getRoutines(): LiveData<List<RoutineTB>>
}