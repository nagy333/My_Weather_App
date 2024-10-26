package com.example.myweatherapp

import android.app.Application
import com.example.myweatherapp.UseCases.isFirstRunUseCase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication:Application() {

}