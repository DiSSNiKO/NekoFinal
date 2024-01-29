package com.example.nekofitness.fragments

import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.nekofitness.DataClasses.FoodMacros
import com.example.nekofitness.R
import com.example.nekofitness.RetrofitAPI.RetrofitInstance
import com.example.nekofitness.broadCastRecievers.DateChangeReceiver
import com.example.nekofitness.database.NekoDBHelper
import com.example.nekofitness.viewModels.NutritionViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Error


class CalorieFragment : Fragment() {
    private val dateChangeReceiver = DateChangeReceiver()
    private lateinit var foodSearchInput: EditText
    private lateinit var fetchFoodBtn: MaterialButton
    private lateinit var dailyFoodScrollView:LinearLayout
    private lateinit var db: NekoDBHelper
    private lateinit var chosenDinner: FoodMacros
    private lateinit var totalDailyCalories: TextView
    private lateinit var totalDailyProtein: TextView
    private lateinit var totalDailyFats: TextView
    private lateinit var totalDailyCarbs: TextView
    private lateinit var totalDailyFiber: TextView
    private lateinit var proteinPercentage: TextView
    private lateinit var fatPercentage: TextView
    private lateinit var carbPercentage: TextView
    private lateinit var deleteLogsDialog: Dialog
    private lateinit var exitDialog: MaterialButton
    private lateinit var confirmDelete: MaterialButton
    private lateinit var deleteTempLogs: MaterialButton
    private val NutritionViewModel: NutritionViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = NekoDBHelper(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calorie, container, false)
        foodSearchInput = view.findViewById(R.id.foodSearchInput)
        fetchFoodBtn = view.findViewById(R.id.fetchFoodBtn)
        dailyFoodScrollView = view.findViewById(R.id.dailyFoodScrollView)
        chosenDinner = FoodMacros()
        totalDailyCalories = view.findViewById(R.id.totalDailyCalories)
        totalDailyCarbs = view.findViewById(R.id.totalDailyCarbs)
        totalDailyFats = view.findViewById(R.id.totalDailyFats)
        totalDailyFiber = view.findViewById(R.id.totalDailyFiber)
        totalDailyProtein = view.findViewById(R.id.totalDailyProtein)
        proteinPercentage = view.findViewById(R.id.proteinPercentage)
        fatPercentage = view.findViewById(R.id.fatPercentage)
        carbPercentage = view.findViewById(R.id.carbPercentage)
        deleteTempLogs = view.findViewById(R.id.deleteTempLogs)
        dialogShenanigans()
        fetchFoodBtn.setOnClickListener {
            fetchFoodDataAndMoveToDB()
        }
        deleteTempLogs.setOnClickListener {
            deleteLogsDialog.show()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NutritionViewModel.dataList.observe(viewLifecycleOwner, { newDataList ->
            updateTotals(newDataList)
        })
        val macros= db.getTempMacros()
        for(log in macros){
            createFoodView(log, layoutInflater)
        }
        NutritionViewModel.updateDataList(macros)
    }
    private fun insertFoodToTempTable(chosenDinner: FoodMacros) {
        db.addTempMacroLog(chosenDinner)
        foodSearchInput.clearFocus()
        foodSearchInput.setText("")
        NutritionViewModel.updateDataList(db.getTempMacros())
    }
    fun fetchFoodDataAndMoveToDB() {
        viewLifecycleOwner.lifecycleScope.launch {
            val response = try {
                RetrofitInstance.api.fetchMacros(foodSearchInput.text.toString().toLowerCase())
            } catch (e: IOException){
                Toast.makeText(requireContext(),"Error, check internet connection", Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(requireContext(),"Bad response", Toast.LENGTH_SHORT).show()
                return@launch
            }
            if(response.isSuccessful && response.body() != null){
                if(response.body()!!.size>0){
                    val fetchedchosenDinner = response.body()!![0]
                    chosenDinner.name_and_amount = inputStringFormatting(foodSearchInput.text.toString().toLowerCase())
                    chosenDinner.fat_total_g = fetchedchosenDinner.fat_total_g
                    chosenDinner.calories = fetchedchosenDinner.calories
                    chosenDinner.carbohydrates_total_g = fetchedchosenDinner.carbohydrates_total_g
                    chosenDinner.protein_g = fetchedchosenDinner.protein_g
                    chosenDinner.fiber_g = fetchedchosenDinner.fiber_g
                    insertFoodToTempTable(chosenDinner)
                    createFoodView(chosenDinner, layoutInflater)
                } else {
                    Toast.makeText(requireContext(),"No food found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun updateTotals(logs:ArrayList<FoodMacros>){
        var totalCal = 0
        var totalCila = 0
        var totalBochko = 0
        var totalCximi = 0
        var totalNaxshirWyali = 0
        for (log in logs){
            totalCal+=log.calories.toFloat().toInt()
            totalBochko+=log.fiber_g.toFloat().toInt()
            totalCila+=log.protein_g.toFloat().toInt()
            totalCximi+=log.fat_total_g.toFloat().toInt()
            totalNaxshirWyali+=log.carbohydrates_total_g.toFloat().toInt()
        }
        totalDailyProtein.text = "Total protein: "+totalCila.toString()+"g"
        totalDailyCalories.text = "Total calories: "+totalCal.toString()+" KCal"
        totalDailyFiber.text = "Total fiber: "+totalBochko.toString()+"g"
        totalDailyFats.text = "Total fats: "+totalCximi.toString()+"g"
        totalDailyCarbs.text ="Total carbs: "+ totalNaxshirWyali.toString()+"g"



        var cilapercentage = (((totalCila.toFloat()*4.0)/(totalCal.toFloat()))*100.0).toInt()
        var fatpercentage = (((totalCximi.toFloat()*9.0)/(totalCal.toFloat()))*100.0).toInt()
        var shaqaripercentage = (((totalNaxshirWyali.toFloat()*4.0)/(totalCal.toFloat()))*100.0).toInt()

        proteinPercentage.text = cilapercentage.toString()+"%"
        fatPercentage.text = fatpercentage.toString()+"%"
        carbPercentage.text = shaqaripercentage.toString()+"%"
    }

    fun createFoodView(chosenDinner:FoodMacros,inflater: LayoutInflater){
        val vviiuu = inflater.inflate(R.layout.nutrition_log_view,dailyFoodScrollView,false)
        vviiuu.findViewById<TextView>(R.id.foodNameAndAmount).text = chosenDinner.name_and_amount
        vviiuu.findViewById<TextView>(R.id.calorieTextViu).text = "KCal: " + chosenDinner.calories
        vviiuu.findViewById<TextView>(R.id.proteinTextViu).text = "Protein: " + chosenDinner.protein_g
        vviiuu.findViewById<TextView>(R.id.fatTextViu).text = "Fats: " + chosenDinner.fat_total_g
        vviiuu.findViewById<TextView>(R.id.carbTextViu).text = "Carbs: " + chosenDinner.carbohydrates_total_g
        vviiuu.findViewById<TextView>(R.id.fiberTextViu).text = "Fiber: " + chosenDinner.fiber_g
        dailyFoodScrollView.addView(vviiuu)
    }

    fun inputStringFormatting(str:String): String {
        val strarr = str.split(" ")
        var newstr = ""
        try {
            var count = 0
            for(i in strarr){
                if(count!=0){
                    newstr+=i.capitalize()+" "
                } else {
                    newstr+=strarr[0]+" "
                }
                count++
            }
        } catch (e:Error){
            return "undefined"
        }
        return newstr.trim()
    }

    private fun dialogShenanigans() {
        deleteLogsDialog = Dialog(requireContext())
        deleteLogsDialog.setContentView(R.layout.log_deletion_dialog)
        deleteLogsDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        deleteLogsDialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.dialogbackground))
        deleteLogsDialog.setCancelable(false)

        exitDialog =  deleteLogsDialog.findViewById(R.id.exitDeletion)
        confirmDelete = deleteLogsDialog.findViewById(R.id.confirmDeletion)

        exitDialog.setOnClickListener {
            deleteLogsDialog.dismiss()
        }

        confirmDelete.setOnClickListener {
            db.clearTempFoodLogs()
            NutritionViewModel.updateDataList(db.getTempMacros())
            deleteLogsDialog.dismiss()
            dailyFoodScrollView.removeAllViews()
        }



    }
}