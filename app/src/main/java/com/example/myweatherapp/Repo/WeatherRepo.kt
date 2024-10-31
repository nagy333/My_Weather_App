package com.example.myweatherapp.Repo

import android.content.Context
import android.util.Log
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

   private val lat= GetCurrentLocation.getLocation(context)[0].toDouble()
   private val lon= GetCurrentLocation.getLocation(context)[1].toDouble()


   val apiState = MutableStateFlow(ApiUiState())


   fun getCurrentWeatherData() = currentDao.getCurrentWeatherData()


   fun getDailyWeatherData() = currentDao.getDailyWeatherData()


   fun getHourlyForecastData() = currentDao.getHourlyForecastData()


    fun getWeatherDetails() = currentDao.getWeatherDetails()

   suspend fun getDailyWeatherCount() = currentDao.getDailyCount()

   suspend fun getCurrentWeatherCount() = currentDao.getCurrentCount()

   suspend fun getHourlyWeatherCount() = currentDao.getHourlyCount()

   val networkState= CheckConnectivity.checkNetworkStatus(context)

   init {


      storeCurrentWeatherInDatabase(currentDao,0,context)

      storeDailyWeatherInDatabase(currentDao,0,null)

      storeHourlyForecastInDatabase(currentDao,0,null)

   }


   @OptIn(DelicateCoroutinesApi::class)
   fun storeCurrentWeatherInDatabase(currentDao: CurrentWeatherDAO, code:Int,context: Context) {

      GlobalScope.launch {
         try {
            apiState.update { it.copy(isLoading = true) }
            Log.d("Mary",GetCurrentLocation.getLocation(context).toString())


            val response = currentWeatherApiService.getRealTimeWeatherByCoordinates(lat = lat, lon = lon)

            if (response.isSuccessful) {

               val currentWeatherData = parseCurrentDataFromApiToDb(response,code)

               val weatherDetails = parseDetailsDataFromApiToDB(response,code)
               Log.d("details",weatherDetails.sunrise.toString())

               currentDao.insertCurrentWeatherData(currentWeatherData)

               currentDao.insertWeatherDetails(weatherDetails)
               apiState.update { it.copy(isLoading = false, isSuccessful = true) }


            } else {
               Log.d("hany", response.message().toString())
               apiState.update { it.copy(isSuccessful = false, isLoading = false, isError = true) }
            }
         } catch (e: Exception) {
            Log.d("current", e.message.toString())
            apiState.update { it.copy(isSuccessful = false, isLoading = false, isError = true) }
         }

      }
   }


   @OptIn(DelicateCoroutinesApi::class)
   fun storeLocationWeather(currentDao: CurrentWeatherDAO,cityName:String) {

      GlobalScope.launch {
         try {
            apiState.update { it.copy(isLoading = true) }

            val response = currentWeatherApiService.getRealTimeWeatherByCityName(cityName = cityName)
            val count=currentDao.getCurrentCount()
            Log.d("count", count.toString())

            if (response.isSuccessful) {

               val currentWeatherData = parseCurrentDataFromApiToDb(response,count)
               val weatherDetails = parseDetailsDataFromApiToDB(response,count)

               currentDao.insertCurrentWeatherData(currentWeatherData)

               currentDao.insertWeatherDetails(weatherDetails)
               apiState.update { it.copy(isLoading = false, isSuccessful = true) }


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
   fun storeDailyWeatherInDatabase(dao: CurrentWeatherDAO,count:Int,cityName: String?) {
      GlobalScope.launch {
         try {

            val response =if (count==0)
               dailyWeatherApi.getDailyData(lat = lat, lon = lon, cityName = null)
            else dailyWeatherApi.getDailyData(lat = null, lon = null, cityName = cityName)


            if (response.isSuccessful) {
               val c=currentDao.getDailyCount()

               val dataList = if (count==0)
                  parseDailyWeatherFromApiToDB(response,count)
               else parseDailyWeatherFromApiToDB(response,c)

               dao.insertDailyWeatherData(dataList)

               Log.d("daily", dataList.size.toString())
            } else {
               Log.d("daily", response.errorBody().toString())
            }
         } catch (e: Exception) {
            Log.d("daily", e.message.toString())

         }

      }

   }


   @OptIn(DelicateCoroutinesApi::class)
   fun storeHourlyForecastInDatabase(dao: CurrentWeatherDAO,count:Int,cityName: String?) {
      GlobalScope.launch {
         try {
            val response = if (count==0)
               hourlyApi.getHourlyData(lat = lat,lon=lon,cityName=null)
            else hourlyApi.getHourlyData(lat = null,lon=null,cityName=cityName)

            if (response.isSuccessful) {
               val c=currentDao.getHourlyCount()

               val hourlyList = if (count==0)
                  parseHourlyDataFromApiToDB(response,count)
               else parseHourlyDataFromApiToDB(response,c)

               dao.insertHourlyForecastData(hourlyList)

               Log.d("dao", hourlyList.size.toString())
            }
            else {
               Log.d("hourlyErrorResponse", response.errorBody().toString())
            }
         } catch (e: Exception) {
            Log.d("hourlyError", e.message.toString())
         }
      }
   }

}
