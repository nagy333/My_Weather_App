package com.example.myweatherapp.States

data class LocationScreenUiState(
    var searchText:String,
    val locationList:List<LocationCardUIState>)


class LocationCardUIState(
    val cityName:String,
    val temp:String,
    val weatherCondition:String
)


