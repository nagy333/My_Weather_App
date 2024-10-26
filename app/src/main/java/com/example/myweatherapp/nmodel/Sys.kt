package com.example.myweatherapp.nmodel


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("sunset")
    val sunset: Int? = null
)