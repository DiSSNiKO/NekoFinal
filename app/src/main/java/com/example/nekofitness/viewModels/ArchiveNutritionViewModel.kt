package com.example.nekofitness.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nekofitness.DataClasses.ArchiveFoodMacros

class ArchiveNutritionViewModel : ViewModel() {
    private val _dataList = MutableLiveData<ArrayList<ArchiveFoodMacros>>()
    val dataList: LiveData<ArrayList<ArchiveFoodMacros>> get() = _dataList

    fun updateDataList(newDataList: ArrayList<ArchiveFoodMacros>) {
        _dataList.value = newDataList
    }
}