package com.example.myweatherapp.Parsing

import com.example.myweatherapp.DB.DetailsEntity
import com.example.myweatherapp.Model.WeatherDataClass
import com.example.myweatherapp.States.DetailsCardUiState
import com.example.myweatherapp.UseCases.TimeUseCases
import retrofit2.Response


fun parseDetailsDataFromDbToUiState(details:DetailsEntity):DetailsCardUiState{
    return DetailsCardUiState(
        feelsLike = details.feelsLike,
        humidity = details.humidity,
        wind = details.wind,
        airPres = details.airPres,
        sunrise = details.sunrise,
        sunset = details.sunset
    )
}

fun parseDetailsDataFromApiToDB(response: Response<WeatherDataClass>,id:Int):DetailsEntity{
    return DetailsEntity(
        feelsLike =response.body()!!.data!![0]!!.appTemp!!.toInt().toString(),
        //feelsLike = "35",
        humidity =response.body()!!.data!![0]!!.rh!!.toInt().toString(),
        //humidity = "20",
        wind =response.body()!!.data!![0]!!.windSpd!!.toInt().toString(),
        //wind = "16",
        airPres =response.body()!!.data!![0]!!.pres!!.toDouble().toString(),
        //airPres = "100",
        sunrise = TimeUseCases.getSunriseTime(response.body()!!.data!![0]!!.sunrise!!),
        //sunrise = "6:45",
        sunset = TimeUseCases.getSunsetTime(response.body()!!.data!![0]!!.sunset!!),
        //sunset = "7:00",
        id = id,

        )
}