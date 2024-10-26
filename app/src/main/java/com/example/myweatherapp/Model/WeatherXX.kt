package com.example.myweatherapp.Model


import com.google.gson.annotations.SerializedName

data class WeatherXX(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String? = null
)