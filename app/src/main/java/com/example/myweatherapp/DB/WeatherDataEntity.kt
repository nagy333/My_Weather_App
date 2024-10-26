package com.example.myweatherapp.DB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CurrentWeatherData")
data class WeatherDataEntity(
    @PrimaryKey(autoGenerate = false)val id:Int,
    val dayName:String?="Monday",
    val cityName:String?="Matay",
    val skyCondition:String?="Clear",
    val countryName:String?="Eg",
    val monthName:String?="April",
    val dayDate:String?="20",
    val date:String="20/10,2024",
    val temp:String?="25",
    val time:String?="5:55",
)
