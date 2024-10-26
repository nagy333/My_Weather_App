package com.example.myweatherapp.Model


import com.google.gson.annotations.SerializedName

data class DailyWeatherModel(
    @SerializedName("city_name")
    val cityName: String? = "",
    @SerializedName("country_code")
    val countryCode: String? = "",
    @SerializedName("data")
    val `data`: List<DataX>? = listOf(),
    @SerializedName("lat")
    val lat: Double? = 0.0,
    @SerializedName("lon")
    val lon: Double? = 0.0,
    @SerializedName("state_code")
    val stateCode: String? = "",
    @SerializedName("timezone")
    val timezone: String? = ""
)