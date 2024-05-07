package com.example.assignemt1

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.assignemt1.ui.theme.Assignemt1Theme
import com.example.screens.BottomNavigationBar
import androidx.activity.viewModels
import androidx.annotation.RequiresApi

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignemt1Theme {
                // A surface container using the 'background' color from the theme
                BottomNavigationBar()
            }
        }
    }
}

