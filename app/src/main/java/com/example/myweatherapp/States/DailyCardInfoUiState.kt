package com.example.myweatherapp.States

data class DailyCardInfoUiState(
            val dayName: String? = "Monday",
            val weatherCondition:String?="Sunny",
            val maxTemp:String?="30",
            val minTemp:String?="20",
            val image:Int?=null
)

