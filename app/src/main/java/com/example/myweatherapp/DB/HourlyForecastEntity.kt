package com.example.myweatherapp.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HourlyForecast")
data class HourlyForecastEntity(
    @PrimaryKey val id:Int,
    val time:String,
    val temp:String
)
