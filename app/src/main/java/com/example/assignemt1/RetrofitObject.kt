package com.example.assignemt1

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private val BASE_URL = "https://api.calorieninjas.com/"
    val retrofitService: FoodApi by lazy{
        Log.i("RetrofitObject", "called")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(FoodApi::class.java)
    }
}