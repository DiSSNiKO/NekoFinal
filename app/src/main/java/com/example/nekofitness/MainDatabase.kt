package com.example.nekofitness

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RoutineTB::class, ExerciseTB::class],
    version = 1
)
abstract class MainDatabase:RoomDatabase() {
    abstract fun routineDao() : RoutineTBDao
    abstract fun exerciseDao() : ExerciseTBDao
    companion object {
        @Volatile
        private var INSTANCE : MainDatabase? = null
        fun getDatabase(context: Context):MainDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}