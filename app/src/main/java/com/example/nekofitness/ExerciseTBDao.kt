package com.example.nekofitness

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ExerciseTBDao {
    @Upsert
    suspend fun insertExercise(exerciseTB: ExerciseTB)
    @Delete
    suspend fun deleteExercise(exerciseTB: ExerciseTB)
    @Query("SELECT * FROM ExerciseTB")
    fun getExercise(): LiveData<List<ExerciseTB>>
}