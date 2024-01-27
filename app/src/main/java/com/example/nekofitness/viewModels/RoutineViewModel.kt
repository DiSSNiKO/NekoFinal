package com.example.nekofitness.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nekofitness.DataClasses.Routines

class RoutineViewModel : ViewModel() {
    private val _dataList = MutableLiveData<ArrayList<Routines>>()
    val dataList: LiveData<ArrayList<Routines>> get() = _dataList

    fun updateDataList(newDataList: ArrayList<Routines>) {
        _dataList.value = newDataList
    }
}