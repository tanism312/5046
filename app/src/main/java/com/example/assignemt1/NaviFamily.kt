package com.example.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignemt1.Community
import com.example.assignemt1.CustomBlack
import com.example.assignemt1.Dashboard
import com.example.assignemt1.Details
import com.example.assignemt1.Profile
import com.example.assignemt1.Stats
import com.example.assignemt1.FollowersList
import com.example.assignemt1.Goals
import com.example.assignemt1.Routes


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation (backgroundColor= Color.LightGray ){
                val navBackStackEntry by
                navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavBarItem().NavBarItems().forEach { navItem ->
                    BottomNavigationItem(
                        icon = { Icon(navItem.icon, contentDescription =
                        null) },
                        label = { Text(navItem.label, color = CustomBlack, fontSize = 10.sp,)},
                        selected = currentDestination?.hierarchy?.any {
                            it.route == navItem.route
                        } == true,
                        onClick = {
                            navController.navigate(navItem.route) {

                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = Routes.Dashboard.value,
            Modifier.padding(paddingValues)
        ) {
            composable(Routes.Dashboard.value) {
                Dashboard(navController)
            }
            composable(Routes.Stats.value) {
                Stats(navController)
            }
            composable(Routes.Community.value) {
                Community(navController)
            }
            composable(Routes.Profile.value) {
                Profile(navController)
            }
            composable(Routes.FollowersList.value) {
                FollowersList(navController)
            }
            composable(Routes.Goals.value) {
                Goals(navController)
            }
            composable(Routes.Details.value) {
                Details(navController)
            }
        }
    }
}

