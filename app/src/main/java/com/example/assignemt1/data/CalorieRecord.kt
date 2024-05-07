package com.example.assignemt1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalorieRecord(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val userInput: String,
    val ingredient : String?,
    val calorie: Double?,
    val date: Long?,
    val mealType: String?
)