package com.example.myweatherapp.Api

import com.example.myweatherapp.Model.DailyWeatherModel
import com.example.myweatherapp.Model.HourluForecastModel
import com.example.myweatherapp.Model.WeatherDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(".")
    suspend fun getRealTimeWeatherByCoordinates(
        @Query("lat") lat:Double=28.4173,
        @Query("lon") lon:Double=30.7818,
    @Query("key") apikey:String ="a1ba4430cc2647bb88e2390a62f4ead6"
    ):Response<WeatherDataClass>


    @GET(".")
    suspend fun getRealTimeWeatherByCityName(
        @Query("city") cityName:String="minia",
        @Query("key") apikey:String ="a1ba4430cc2647bb88e2390a62f4ead6"
    ):Response<WeatherDataClass>

    @GET(".")
    suspend fun getDailyData(
        @Query("lat") lat:Double?=28.4173,
        @Query("lon") lon:Double?=30.7818,
        @Query("city") cityName: String?="minia",
        @Query("days") daysNumber: Int?=6,
        @Query("key") apikey:String ="a1ba4430cc2647bb88e2390a62f4ead6"
    ):Response<DailyWeatherModel>

    @GET(".")
    suspend fun getHourlyData(
        @Query("lat") lat:Double?=28.4173,
        @Query("lon") lon:Double?=30.7818,
        @Query("city") cityName: String?="minia",
        @Query("hours") hours:Int?= 24,
        @Query("key") apikey:String ="a1ba4430cc2647bb88e2390a62f4ead6"
    ):Response<HourluForecastModel>
}