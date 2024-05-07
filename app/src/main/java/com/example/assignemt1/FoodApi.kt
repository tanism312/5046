package com.example.assignemt1

import com.example.assignemt1.data.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface FoodApi {
    @GET("v1/nutrition")
    suspend fun getNutritionInfo(
        @Query("query") query: String,
        @Header("X-Api-Key") API_KEY: String
    ): SearchResponse
}

