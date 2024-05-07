package com.example.assignemt1.data

import com.example.assignemt1.R

data class Tracker(
    val name: String,
    val description: String,
    val image: Int,
    val progress: Double)

val Trackers = listOf<Tracker>(
    Tracker("Breakfast","Breakfast tracker", R.drawable.breakfast,0.5),
    Tracker("Lunch","Lunch tracker", R.drawable.lunch,0.36),
    Tracker("Dinner","Dinner tracker", R.drawable.dinner,0.8),
    Tracker("Snack","Snack tracker", R.drawable.fruits,1.1),


    )