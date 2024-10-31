package com.example.myweatherapp.Model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("app_temp")
    val appTemp: Double? = null,
    @SerializedName("aqi")
    val aqi: Int? = null,
    @SerializedName("city_name")
    val cityName: String? = null,
    @SerializedName("clouds")
    val clouds: Int? = null,
    @SerializedName("country_code")
    val countryCode: String? = null,
    @SerializedName("datetime")
    val datetime: String? = null,
    @SerializedName("dewpt")
    val dewpt: Double? = null,
    @SerializedName("dhi")
    val dhi: Int? = null,
    @SerializedName("dni")
    val dni: Int? = null,
    @SerializedName("elev_angle")
    val elevAngle: Double? = null,
    @SerializedName("ghi")
    val ghi: Int? = null,
    @SerializedName("gust")
    val gust: Double? = null,
    @SerializedName("h_angle")
    val hAngle: Int? = null,
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lon")
    val lon: Double? = null,
    @SerializedName("ob_time")
    val obTime: String? = null,
    @SerializedName("pod")
    val pod: String? = null,
    @SerializedName("precip")
    val precip: Int? = null,
    @SerializedName("pres")
    val pres: Double? = null,
    @SerializedName("rh")
    val rh: Int? = null,
    @SerializedName("slp")
    val slp: Double? = null,
    @SerializedName("snow")
    val snow: Int? = null,
    @SerializedName("solar_rad")
    val solarRad: Double? = null,
    @SerializedName("sources")
    val sources: List<String?>? = null,
    @SerializedName("state_code")
    val stateCode: String? = null,
    @SerializedName("station")
    val station: String? = null,
    @SerializedName("sunrise")
    val sunrise: String? = null,
    @SerializedName("sunset")
    val sunset: String? = null,
    @SerializedName("temp")
    val temp: Double? = null,
    @SerializedName("timezone")
    val timezone: String? = null,
    @SerializedName("ts")
    val ts: Int? = null,
    @SerializedName("uv")
    val uv: Int? = null,
    @SerializedName("vis")
    val vis: Int? = null,
    @SerializedName("weather")
    val weather: Weather? = null,
    @SerializedName("wind_cdir")
    val windCdir: String? = null,
    @SerializedName("wind_cdir_full")
    val windCdirFull: String? = null,
    @SerializedName("wind_dir")
    val windDir: Int? = null,
    @SerializedName("wind_spd")
    val windSpd: Double? = null
)