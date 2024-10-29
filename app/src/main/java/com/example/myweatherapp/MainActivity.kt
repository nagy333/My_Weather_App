package com.example.myweatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myweatherapp.Composables.MyNavigation
import com.example.myweatherapp.Screens.MainScreen
import com.example.myweatherapp.UseCases.GetCurrentLocation
import com.example.myweatherapp.UseCases.isFirstRunUseCase
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var ok=0
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            //GetCurrentLocation.GetLocation(this)
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