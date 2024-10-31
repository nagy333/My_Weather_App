package com.example.myweatherapp.Parsing

import com.example.myweatherapp.DB.WeatherDataEntity
import com.example.myweatherapp.States.LocationCardUIState
import com.example.myweatherapp.States.LocationScreenUiState

fun parseLocationDataFromDbToUiState(list:List<WeatherDataEntity>):List<LocationCardUIState>{
    val locations=ArrayList<LocationCardUIState>()
    list.forEach {
        val item=LocationCardUIState(
            cityName = it.cityName!!,
            temp = it.temp!!,
            weatherCondition = it.skyCondition!!
        )
        locations.add(item)
    }
    return locations
}