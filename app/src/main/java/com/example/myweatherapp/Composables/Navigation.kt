package com.example.myweatherapp.Composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myweatherapp.Screens.LocationScreen
import com.example.myweatherapp.Screens.MainScreen
import com.example.myweatherapp.Screens.MyPagerScreen

@Composable
fun MyNavigation(){
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = "MainScreen") {
        composable("MainScreen"){ MainScreen(navController,0) }

        composable("LocationScreen"){ LocationScreen(navController) }

        composable("PagerScreen"){ MyPagerScreen(navController,0) }
    }
}