package com.example.myweatherapp.Parsing

import com.example.myweatherapp.DB.WeatherDataEntity
import com.example.myweatherapp.Model.WeatherDataClass
import com.example.myweatherapp.States.CurrentWeatherUiState
import com.example.myweatherapp.States.MainScreenUiState
import com.example.myweatherapp.UseCases.TimeUseCases
import retrofit2.Response


fun parseCurrentDataFromDBtoUiState(data:WeatherDataEntity):CurrentWeatherUiState{
    return CurrentWeatherUiState(
        dayName = data.dayName,
        cityName = data.cityName,
        countryName = data.countryName,
        monthName = data.monthName,
        dayNumber = data.dayDate,
        dateNumber = data.date,
        time = data.time,
        weatherCondition = data.skyCondition,
        Temp = data.temp,
        )
}
fun parseCurrentDataFromApiToDb(response:Response<WeatherDataClass>):WeatherDataEntity{
    return WeatherDataEntity(
        cityName = response.body()!!.data!![0]!!.cityName!!,
        skyCondition = response.body()!!.data!![0]!!.weather!!.description!!,
        countryName =response.body()!!.data!![0]!!.countryCode!!,
        monthName = TimeUseCases.convertNumberToMonth(response.body()!!.data!![0]!!.datetime!!.substring(5,7).toString().toInt().toString()),
        dayDate =  response.body()!!.data!![0]!!.datetime!!.substring(8,10).toString().toInt().toString(),
        date = "${response.body()!!.data!![0]!!.datetime!!.substring(8,10).toInt()}" +
                "/${response.body()!!.data!![0]!!.datetime!!.substring(5,7).toInt()}/2024",
        temp =response.body()!!.data!![0]!!.temp!!.toInt().toString(),
        time = TimeUseCases.getCurrentTime(),
        dayName = TimeUseCases.getDayName(response.body()!!.data!![0]!!.datetime!!),
        id = 1
    )
}

