package com.example.myweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myweatherapp.Composables.MyNavigation
import com.example.myweatherapp.Screens.MainScreen
import com.example.myweatherapp.UseCases.GetCurrentLocation
import com.example.myweatherapp.UseCases.isFirstRunUseCase
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GetCurrentLocation.GetLocation(this)
            isFirstRunUseCase.initSharedPrefs(this)
            MyWeatherAppTheme {
           MyNavigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWeatherAppTheme {
        MyNavigation()
    }
}