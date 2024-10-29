package com.example.myweatherapp.Repo

import android.content.Context
import android.util.Log
import androidx.compose.material3.Checkbox
import com.example.myweatherapp.Api.CurrentWeatherApi.currentWeatherApiService
import com.example.myweatherapp.Api.DailyWeatherApi.dailyWeatherApi
import com.example.myweatherapp.Api.HourlyForecastApi.hourlyApi
import com.example.myweatherapp.DB.CurrentWeatherDAO
import com.example.myweatherapp.DB.WeatherDataEntity
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

   private var count=1


   val networkState= CheckConnectivity.checkNetworkStatus(context)
   val currentList:ArrayList<WeatherDataEntity> = ArrayList()

   init {


      storeCurrentWeatherInDatabase(currentDao,0,"",context)

      storeDailyWeatherInDatabase(currentDao)

      storeHourlyForecastInDatabase(currentDao)

   }


   @OptIn(DelicateCoroutinesApi::class)
   fun storeCurrentWeatherInDatabase(currentDao: CurrentWeatherDAO,code:Int=count,cityName:String,context: Context) {

      GlobalScope.launch {
         try {
            apiState.update { it.copy(isLoading = true) }
            val lat=GetCurrentLocation.getLocation(context).get(0).toDouble()
            val lon=GetCurrentLocation.getLocation(context).get(1).toDouble()
            Log.d("Mary",GetCurrentLocation.getLocation(context = context).toString())
//           currentDao.getCount().collect{
//               count=it
//            }

            val response = if (code==0)
               currentWeatherApiService.getRealTimeWeatherByCoordinates(lat = lat, lon = lon)
            else currentWeatherApiService.getRealTimeWeatherByCityName(cityName = cityName)

            if (response.isSuccessful) {

               val currentWeatherData = parseCurrentDataFromApiToDb(response,code)
               currentList.add(code,currentWeatherData)

               val weatherDetails = parseDetailsDataFromApiToDB(response)

               currentDao.insertCurrentWeatherData(currentList)
               Log.d("list",currentList.size.toString())

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
   fun storeDailyWeatherInDatabase(dao: CurrentWeatherDAO) {
      GlobalScope.launch {
         try {

            val response = dailyWeatherApi.getDailyData()

            if (response.isSuccessful) {

               val dataList = parseDailyWeatherFromApiToDB(response)

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
