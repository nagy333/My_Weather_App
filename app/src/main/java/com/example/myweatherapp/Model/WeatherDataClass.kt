package com.example.myweatherapp.Model


import com.google.gson.annotations.SerializedName

data class WeatherDataClass(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("data")
    val `data`: List<Data?>? = null
)