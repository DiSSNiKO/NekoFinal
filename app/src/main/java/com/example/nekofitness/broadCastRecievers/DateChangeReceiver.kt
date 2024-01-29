package com.example.nekofitness.broadCastRecievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.nekofitness.DataClasses.ArchiveFoodMacros
import com.example.nekofitness.DataClasses.FoodMacros
import com.example.nekofitness.database.NekoDBHelper
import java.time.LocalDate

class DateChangeReceiver: BroadcastReceiver() {
    private lateinit var db: NekoDBHelper
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        db = NekoDBHelper(context!!.applicationContext)
        if (intent?.action == Intent.ACTION_DATE_CHANGED){
            createDayArchive(db.getTempMacros())
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun createDayArchive(logs:ArrayList<FoodMacros>){
        if(logs.size>0){
            var totalCal = 0
            var totalCila = 0
            var totalBochko = 0
            var totalCximi = 0
            var totalNaxshirWyali = 0
            var foods = ""
            for (log in logs){
                totalCal+=log.calories.toFloat().toInt()
                totalBochko+=log.fiber_g.toFloat().toInt()
                totalCila+=log.protein_g.toFloat().toInt()
                totalCximi+=log.fat_total_g.toFloat().toInt()
                totalNaxshirWyali+=log.carbohydrates_total_g.toFloat().toInt()
                foods+=log.name_and_amount+", "
            }
            foods = foods.trim()
            var foods2 = ""
            val ew = foods.length
            var weSayNoToTheExtraComma = 0
            while(weSayNoToTheExtraComma<ew-1){
                foods2+=foods[weSayNoToTheExtraComma]
                weSayNoToTheExtraComma++
            }
            val nowDate = LocalDate.now()
            val archiveMacro = ArchiveFoodMacros(foods2,totalCal.toString()+"KCal",
                totalCximi.toString()+"g",
                totalCila.toString()+"g",
                totalNaxshirWyali.toString()+"g",
                totalBochko.toString()+"g",
                nowDate.toString()
            )
            db.addArcMacroLog(archiveMacro)
            println(db.getArcMacros())
            db.clearTempFoodLogs()
        }
    }
}