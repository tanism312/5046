package com.example.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.assignemt1.Routes

data class NavBarItem (
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    fun NavBarItems(): List<NavBarItem> {
        return listOf(
            NavBarItem(
                label = "Dashboard",
                icon = Icons.Filled.Home,
                route = Routes.Dashboard.value
            ),
            NavBarItem(
                label = "Stats",
                icon = Icons.Filled.Settings,
                route = Routes.Stats.value
            ),
            NavBarItem(
                label = "Community",
                icon = Icons.Filled.Menu,
                route = Routes.Community.value
            ),
            NavBarItem(
                label = "Profile",
                icon = Icons.Filled.Person,
                route = Routes.Profile.value
            )
        )
    }
}