package com.example.myweatherapp.Api

import com.example.myweatherapp.Model.DailyWeatherModel
import com.example.myweatherapp.Model.HourluForecastModel
import com.example.myweatherapp.Model.WeatherDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(".")
    suspend fun getRealTimeWeather(
        @Query("lat") lat:Double=28.4173,
        @Query("lon") lon:Double=30.7818,
    @Query("key") apikey:String ="04003d1c827142778a1ebf1680cf89e9"
    ):Response<WeatherDataClass>

    @GET(".")
    suspend fun getDailyData(
        @Query("lat") lat:Double=28.4173,
        @Query("lon") lon:Double=30.7818,
        @Query("key") apikey:String ="04003d1c827142778a1ebf1680cf89e9"
    ):Response<DailyWeatherModel>

    @GET(".")
    suspend fun getHourlyData(
        @Query("lat") lat:Double=28.4173,
        @Query("lon") lon:Double=30.7818,
        @Query("key") apikey:String ="04003d1c827142778a1ebf1680cf89e9"
    ):Response<HourluForecastModel>
}