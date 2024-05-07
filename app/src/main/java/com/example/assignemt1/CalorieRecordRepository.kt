package com.example.assignemt1

import com.example.assignemt1.data.CalorieRecord


import android.app.Application
import kotlinx.coroutines.flow.Flow

class CalorieRecordRepository (application: Application) {

    private var calorieRecordDao: CalorieRecordDAO =
        CalorieRecordDatabase.getDatabase(application).calorieRecordDAO()

    val allCalorieRecords: Flow<List<CalorieRecord>> = calorieRecordDao.getAllCalorieRecords()

    fun getCalorieRecordsByMealType(mealType: String): Flow<List<CalorieRecord>> {
        return calorieRecordDao.getCalorieRecordsByMealType(mealType)
    }
    fun getCalorieRecordsByDateAndMealType(date:Long, mealType: String): Flow<List<CalorieRecord>> {
        return calorieRecordDao.getCalorieRecordsByDateAndMealType(date,mealType)
    }
    suspend fun insert(calorieRecord: CalorieRecord) {
        calorieRecordDao.insertCalorieRecord(calorieRecord)
    }

    suspend fun delete(calorieRecord: CalorieRecord) {
        calorieRecordDao.deleteCalorieRecord(calorieRecord)
    }

    suspend fun update(calorieRecord: CalorieRecord) {
        calorieRecordDao.updateCalorieRecord(calorieRecord)
    }
}