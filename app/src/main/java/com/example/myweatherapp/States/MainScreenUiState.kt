package com.example.myweatherapp.States

import com.example.myweatherapp.R

data class MainScreenUiState(
    val currentWeatherData : CurrentWeatherUiState? = CurrentWeatherUiState(),
    val dailyCardInfo : List<DailyCardInfoUiState>? = listOf(
        DailyCardInfoUiState(),
        DailyCardInfoUiState(),
        DailyCardInfoUiState(),
        DailyCardInfoUiState(),
        DailyCardInfoUiState(),
        DailyCardInfoUiState()
    )
)

data class CurrentWeatherUiState(
    val dayName : String? = "Monday",
    val cityName: String? = "malawy",
    val countryName : String? = "Eg",
    val monthName : String? =" November",
    val dayNumber : String? = "29",
    val dateNumber : String? = "20/9/2024",
    val time : String? = "10:55",
    val weatherCondition : String? = "sunny",
    val image : Int? = R.drawable.cloudy,
    val Temp : String? = "100",
)


