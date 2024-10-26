package com.example.myweatherapp.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DailyWeather")
data class DailyWeatherEntity(
    @PrimaryKey val id:Int?=null,
    val day:String?=null,
    val weatherCondition:String?=null,
    val maxTemp:String?=null,
    val minTemp:String?=null,
)
