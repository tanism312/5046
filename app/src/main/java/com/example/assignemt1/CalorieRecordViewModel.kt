package com.example.assignemt1

import android.app.Application
import android.health.connect.datatypes.MealType
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.assignemt1.data.CalorieRecord

class CalorieRecordViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: CalorieRecordRepository
    init{
        cRepository = CalorieRecordRepository(application)
    }
    val allCalorieRecords: LiveData<List<CalorieRecord>> = cRepository.allCalorieRecords.asLiveData()

    fun getCalorieRecordsByMealType(mealType: String):LiveData<List<CalorieRecord>>{
       return cRepository.getCalorieRecordsByMealType(mealType).asLiveData()
    }

    fun getCalorieRecordsByDateAndMealType(startOfDay: Long, endOfDay: Long, mealType: String):LiveData<List<CalorieRecord>>{
        return cRepository.getCalorieRecordsByDateAndMealType(startOfDay,endOfDay,mealType).asLiveData()
    }

    fun insertCalorieRecord(calorieRecord: CalorieRecord) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insert(calorieRecord)
    }

    fun updateCalorieRecord(calorieRecord: CalorieRecord) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.update(calorieRecord)
    }

    fun deleteCalorieRecord(calorieRecord: CalorieRecord) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.delete(calorieRecord)
    }
}