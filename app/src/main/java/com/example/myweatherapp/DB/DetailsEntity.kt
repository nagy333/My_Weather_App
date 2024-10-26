package com.example.myweatherapp.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherDetails")
data class DetailsEntity(
   @PrimaryKey(autoGenerate = false) val id:Int,
    val feelsLike:String?=null,
    val humidity:String?=null,
    val wind:String?=null,
    val airPres:String?=null,
    val sunrise:String?=null,
    val sunset:String?=null,
)
