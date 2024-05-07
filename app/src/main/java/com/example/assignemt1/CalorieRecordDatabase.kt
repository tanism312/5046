package com.example.assignemt1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignemt1.data.CalorieRecord

@Database(entities = [CalorieRecord::class], version = 2, exportSchema = false)
abstract class CalorieRecordDatabase : RoomDatabase() {

    abstract fun calorieRecordDAO(): CalorieRecordDAO

    companion object {
        @Volatile
        private var INSTANCE: CalorieRecordDatabase? = null

        fun getDatabase(context: Context): CalorieRecordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalorieRecordDatabase::class.java,
                    "calorieRecord_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}