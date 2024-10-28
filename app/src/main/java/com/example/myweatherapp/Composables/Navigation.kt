package com.example.myweatherapp.Composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myweatherapp.Screens.DetailsScreen
import com.example.myweatherapp.Screens.LocationScreen
import com.example.myweatherapp.Screens.MainScreen

@Composable
fun MyNavigation(){
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = "MainScreen") {
        composable("MainScreen"){ MainScreen(navController) }
        composable("DetailsScreen"){ DetailsScreen(navController) }
        composable("LocationScreen"){ LocationScreen() }
    }
}