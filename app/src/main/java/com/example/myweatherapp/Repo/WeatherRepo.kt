package com.example.myweatherapp.Repo

import android.content.Context
import android.util.Log
import androidx.compose.material3.Checkbox
import com.example.myweatherapp.Api.CurrentWeatherApi.currentWeatherApiService
import com.example.myweatherapp.Api.DailyWeatherApi.dailyWeatherApi
import com.example.myweatherapp.Api.HourlyForecastApi.hourlyApi
import com.example.myweatherapp.DB.CurrentWeatherDAO
import com.example.myweatherapp.Parsing.parseCurrentDataFromApiToDb
import com.example.myweatherapp.Parsing.parseDailyWeatherFromApiToDB
import com.example.myweatherapp.Parsing.parseDetailsDataFromApiToDB
import com.example.myweatherapp.Parsing.parseHourlyDataFromApiToDB
import com.example.myweatherapp.States.ApiUiState
import com.example.myweatherapp.UseCases.CheckConnectivity
import com.example.myweatherapp.UseCases.GetCurrentLocation
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherRepo(private val currentDao:CurrentWeatherDAO,context:Context) {


   val apiState = MutableStateFlow(ApiUiState())


   fun getCurrentWeatherData() = currentDao.getCurrentWeatherData()


   fun getDailyWeatherData() = currentDao.getDailyWeatherData()


   fun getHourlyForecastData() = currentDao.getHourlyForecastData()


   suspend fun getWeatherDetails() = currentDao.getWeatherDetails()

   val networkState= CheckConnectivity.checkNetworkStatus(context)

   init {

      storeCurrentWeatherInDatabase(currentDao)

      storeDailyWeatherInDatabase(currentDao)

      storeHourlyForecastInDatabase(currentDao)

   }


   @OptIn(DelicateCoroutinesApi::class)
   fun storeCurrentWeatherInDatabase(currentDao: CurrentWeatherDAO) {
      GlobalScope.launch {
         try {
            val lat=GetCurrentLocation.getLatAndLon.value[0].toDouble()
            val lon=GetCurrentLocation.getLatAndLon.value[1].toDouble()

            val response = currentWeatherApiService.getRealTimeWeather(lat = lat, lon = lon)

            if (response.isSuccessful) {

               val currentWeatherData = parseCurrentDataFromApiToDb(response)

               val weatherDetails = parseDetailsDataFromApiToDB(response)

               currentDao.insertCurrentWeatherData(currentWeatherData)

               currentDao.insertWeatherDetails(weatherDetails)


            } else {
               Log.d("hany", response.message().toString())
               apiState.update { it.copy(isSuccessful = false, isLoading = false, isError = true) }
            }
         } catch (e: Exception) {
            Log.d("nagy", e.message.toString())
            apiState.update { it.copy(isSuccessful = false, isLoading = false, isError = true) }
         }

      }
   }


   @OptIn(DelicateCoroutinesApi::class)
   fun storeDailyWeatherInDatabase(dao: CurrentWeatherDAO) {
      GlobalScope.launch {
         try {
            apiState.update { it.copy(isLoading = true) }

            val response = dailyWeatherApi.getDailyData()

            if (response.isSuccessful) {

               val dataList = parseDailyWeatherFromApiToDB(response)

               dao.insertDailyWeatherData(dataList)

               apiState.update { it.copy(isLoading = false, isSuccessful = true) }

               Log.d("nagy", dataList.size.toString())
            } else {
               Log.d("nagy", response.errorBody().toString())
            }
         } catch (e: Exception) {
            Log.d("nagy", e.message.toString())

         }

      }

   }


   @OptIn(DelicateCoroutinesApi::class)
   fun storeHourlyForecastInDatabase(dao: CurrentWeatherDAO) {
      GlobalScope.launch {
         try {
            val response = hourlyApi.getHourlyData()

            if (response.isSuccessful) {

               val hourlyList = parseHourlyDataFromApiToDB(response)

               dao.insertHourlyForecastData(hourlyList)

               Log.d("dao", hourlyList.size.toString())
            } else {
               Log.d("hourlyError", response.errorBody().toString())
            }
         } catch (e: Exception) {
            Log.d("hourlyError", e.message.toString())
         }
      }
   }

}
