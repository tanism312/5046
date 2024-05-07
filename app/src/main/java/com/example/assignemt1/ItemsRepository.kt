package com.example.assignemt1

import com.example.assignemt1.data.SearchResponse


class ItemsRepository {
    private val nutritionService = RetrofitObject.retrofitService
    private val API_KEY = "3Hb8gpbl+r9oDlknjf4sxg==x9NA432gRTYfI63c"
    suspend fun getResponse(foodInput: String): SearchResponse {
        return nutritionService.getNutritionInfo(
            foodInput,
            API_KEY
        )
    }
}
