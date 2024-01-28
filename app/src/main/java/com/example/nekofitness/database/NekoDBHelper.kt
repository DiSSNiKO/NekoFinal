package com.example.nekofitness.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.nekofitness.DataClasses.FoodMacros
import com.example.nekofitness.DataClasses.Routines

class NekoDBHelper(context:Context) : SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {
    companion object {
        private val DB_NAME = "NEKODB"
        private val DB_VERSION = 1

        //Routine Table
        private val ROUTINE_TABLE = "routines"
        private val ROUTINE_ID = "id"
        private val ROUTINE_NAME = "name"
        private val ROUTINE_EXERCISES = "exercises"

        //Temp macro log table
        private val TEMP_MACRO_TABLE = "macros_temp"
        private val TEMP_MACRO_ID = "id"
        private val TEMP_MACRO_NAME = "name"
        private val TEMP_MACRO_CALORIES = "calories"
        private val TEMP_MACRO_FIBER = "fiber"
        private val TEMP_MACRO_PROTEIN = "protein"
        private val TEMP_MACRO_FATS = "fats"
        private val TEMP_MACRO_CARBS= "carbs"

        //Archive macro log table
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_ROUTINE_TABLE = "CREATE TABLE $ROUTINE_TABLE ($ROUTINE_ID INTEGER PRIMARY KEY, $ROUTINE_NAME TEXT, $ROUTINE_EXERCISES TEXT)"
        val CREATE_TEMP_MACRO = "CREATE TABLE $TEMP_MACRO_TABLE ($TEMP_MACRO_ID INTEGER PRIMARY KEY, $TEMP_MACRO_NAME TEXT, $TEMP_MACRO_CALORIES TEXT, $TEMP_MACRO_PROTEIN TEXT, $TEMP_MACRO_FATS TEXT, $TEMP_MACRO_FIBER TEXT, $TEMP_MACRO_CARBS TEXT)"
        db?.execSQL(CREATE_ROUTINE_TABLE)
        db?.execSQL(CREATE_TEMP_MACRO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_ROUTINE = "DROP TABLE IF EXISTS $ROUTINE_TABLE"
        val DROP_TABLE_TEMP_MACROS = "DROP TABLE IF EXISTS $TEMP_MACRO_TABLE"
        db?.execSQL(DROP_TABLE_ROUTINE)
        db?.execSQL(DROP_TABLE_TEMP_MACROS)
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
    fun addTempMacroLog(foodMacro:FoodMacros) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TEMP_MACRO_NAME, foodMacro.name_and_amount)
        values.put(TEMP_MACRO_CALORIES, foodMacro.calories)
        values.put(TEMP_MACRO_PROTEIN, foodMacro.protein_g)
        values.put(TEMP_MACRO_FATS, foodMacro.fat_total_g)
        values.put(TEMP_MACRO_FIBER, foodMacro.fiber_g)
        values.put(TEMP_MACRO_CARBS, foodMacro.carbohydrates_total_g)
        db.insert(TEMP_MACRO_TABLE, null, values)
        db.close()
    }
    fun clearRoutines(){
        val db = writableDatabase
        db.delete(ROUTINE_TABLE, null, null)
        db.close();
    }
    fun clearTempFoodLogs(){
        val db = writableDatabase
        db.delete(TEMP_MACRO_TABLE, null, null)
        db.close();
    }
    @SuppressLint("Range")
    fun getTempMacros() : ArrayList<FoodMacros>{
        val foodMacroArr = arrayListOf<FoodMacros>()
        val db = readableDatabase
        val cursor  = db.rawQuery("SELECT * FROM $TEMP_MACRO_TABLE",null)
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    var foodMacro = FoodMacros(
                        name_and_amount = cursor.getString(cursor.getColumnIndex(TEMP_MACRO_NAME)),
                        calories = cursor.getString(cursor.getColumnIndex(TEMP_MACRO_CALORIES)),
                        protein_g = cursor.getString(cursor.getColumnIndex(TEMP_MACRO_PROTEIN)),
                        carbohydrates_total_g = cursor.getString(cursor.getColumnIndex(TEMP_MACRO_CARBS)),
                        fat_total_g = cursor.getString(cursor.getColumnIndex(TEMP_MACRO_FATS)),
                        fiber_g = cursor.getString(cursor.getColumnIndex(TEMP_MACRO_FIBER))
                    )
                    foodMacroArr.add(
                        foodMacro
                    )
                }while(cursor.moveToNext())
            }
        }
        cursor.close()
        return foodMacroArr
    }
}