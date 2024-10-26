package com.example.myweatherapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrentWeatherApi {
     private const val BASE_URL="https://api.weatherbit.io/v2.0/current/"
    val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val currentWeatherApiService= retrofit.create(ApiInterface::class.java)
}