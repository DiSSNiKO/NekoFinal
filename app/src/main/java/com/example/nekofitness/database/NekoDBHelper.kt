package com.example.nekofitness.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.nekofitness.DataClasses.Routines

class NekoDBHelper(context:Context) : SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {
    companion object {
        private val DB_NAME = "NEKODB"
        private val DB_VERSION = 1
        private val ROUTINE_TABLE = "routines"
        private val ROUTINE_ID = "id"
        private val ROUTINE_NAME = "name"
        private val ROUTINE_EXERCISES = "exercises"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_ROUTINE_TABLE = "CREATE TABLE $ROUTINE_TABLE ($ROUTINE_ID INTEGER PRIMARY KEY, $ROUTINE_NAME TEXT, $ROUTINE_EXERCISES TEXT)"
        db?.execSQL(CREATE_ROUTINE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $ROUTINE_TABLE"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }
    @SuppressLint("Range")
    fun getRoutines() : ArrayList<Routines>{
        val routineArray = arrayListOf<Routines>()
        val db = readableDatabase
        val cursor  = db.rawQuery("SELECT * FROM $ROUTINE_TABLE",null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    routineArray.add(
                        Routines(cursor.getString(cursor.getColumnIndex(ROUTINE_NAME)),cursor.getString(cursor.getColumnIndex(ROUTINE_EXERCISES)))
                    )
                }while(cursor.moveToNext())
            }
        }
        cursor.close()
        return routineArray
    }
    @SuppressLint("Range")
    fun getSpecificRoutine(routineNamae: String?) : Routines{
        val routineArray = arrayListOf<Routines>()
        val db = readableDatabase
        val cursor  = db.rawQuery("SELECT * FROM $ROUTINE_TABLE WHERE $ROUTINE_NAME = '$routineNamae'",null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    routineArray.add(
                        Routines(cursor.getString(cursor.getColumnIndex(ROUTINE_NAME)),cursor.getString(cursor.getColumnIndex(ROUTINE_EXERCISES)))
                    )
                }while(cursor.moveToNext())
            }
        }
        cursor.close()
        return routineArray[0]
    }
    fun addRoutine(routine:Routines) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(ROUTINE_NAME, routine.name)
        values.put(ROUTINE_EXERCISES, routine.exercises)
        db.insert(ROUTINE_TABLE, null, values)
        db.close()
    }

    fun clearRoutines(){
        val db = writableDatabase
        db.delete(ROUTINE_TABLE, null, null)
        db.close();
    }
}