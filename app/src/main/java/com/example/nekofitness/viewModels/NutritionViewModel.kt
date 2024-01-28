package com.example.nekofitness.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nekofitness.DataClasses.FoodMacros

class NutritionViewModel : ViewModel() {
    private val _dataList = MutableLiveData<ArrayList<FoodMacros>>()
    val dataList: LiveData<ArrayList<FoodMacros>> get() = _dataList

    fun updateDataList(newDataList: ArrayList<FoodMacros>) {
        _dataList.value = newDataList
    }
}