package com.example.myweatherapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DailyWeatherApi {
    private const val BASE_URL="https://api.weatherbit.io/v2.0/forecast/daily/"
    val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val dailyWeatherApi= retrofit.create(ApiInterface::class.java)
}