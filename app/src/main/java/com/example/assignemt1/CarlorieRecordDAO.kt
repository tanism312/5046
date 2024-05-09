package com.example.assignemt1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.assignemt1.data.CalorieRecord


@Dao
interface CalorieRecordDAO {
    @Query("SELECT * FROM CalorieRecord")
    fun getAllCalorieRecords(): Flow<List<CalorieRecord>>

    @Query("SELECT * FROM CalorieRecord WHERE mealType = :mealType")
    fun getCalorieRecordsByMealType(mealType: String): Flow<List<CalorieRecord>>

    @Query("SELECT * FROM CalorieRecord WHERE date BETWEEN :startOfDay AND :endOfDay AND mealType = :mealType")
    fun getCalorieRecordsByDateAndMealType(startOfDay: Long, endOfDay: Long, mealType: String): Flow<List<CalorieRecord>>

    @Insert
    suspend fun insertCalorieRecord(calorieRecord: CalorieRecord)

    @Update
    suspend fun updateCalorieRecord(calorieRecord: CalorieRecord)

    @Delete
    suspend fun deleteCalorieRecord(calorieRecord: CalorieRecord)
}