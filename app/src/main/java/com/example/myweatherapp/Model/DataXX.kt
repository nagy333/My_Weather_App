package com.example.myweatherapp.Model


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("app_temp")
    val appTemp: Double? = 0.0,
    @SerializedName("clouds")
    val clouds: Int? = 0,
    @SerializedName("clouds_hi")
    val cloudsHi: Int? = 0,
    @SerializedName("clouds_low")
    val cloudsLow: Int? = 0,
    @SerializedName("clouds_mid")
    val cloudsMid: Int? = 0,
    @SerializedName("datetime")
    val datetime: String? = "",
    @SerializedName("dewpt")
    val dewpt: Double? = 0.0,
    @SerializedName("dhi")
    val dhi: Int? = 0,
    @SerializedName("dni")
    val dni: Int? = 0,
    @SerializedName("ghi")
    val ghi: Int? = 0,
    @SerializedName("ozone")
    val ozone: Int? = 0,
    @SerializedName("pod")
    val pod: String? = "",
    @SerializedName("pop")
    val pop: Int? = 0,
    @SerializedName("precip")
    val precip: Double? = null,
    @SerializedName("pres")
    val pres: Int? = 0,
    @SerializedName("rh")
    val rh: Int? = 0,
    @SerializedName("slp")
    val slp: Int? = 0,
    @SerializedName("snow")
    val snow: Int? = 0,
    @SerializedName("snow_depth")
    val snowDepth: Int? = 0,
    @SerializedName("solar_rad")
    val solarRad: Double? = 0.0,
    @SerializedName("temp")
    val temp: Double? = 0.0,
    @SerializedName("timestamp_local")
    val timestampLocal: String? = "",
    @SerializedName("timestamp_utc")
    val timestampUtc: String? = "",
    @SerializedName("ts")
    val ts: Int? = 0,
    @SerializedName("uv")
    val uv: Int? = 0,
    @SerializedName("vis")
    val vis: Double? = 0.0,
    @SerializedName("weather")
    val weather: WeatherXX? = WeatherXX(),
    @SerializedName("wind_cdir")
    val windCdir: String? = "",
    @SerializedName("wind_cdir_full")
    val windCdirFull: String? = "",
    @SerializedName("wind_dir")
    val windDir: Int? = 0,
    @SerializedName("wind_gust_spd")
    val windGustSpd: Double? = 0.0,
    @SerializedName("wind_spd")
    val windSpd: Double? = 0.0
)