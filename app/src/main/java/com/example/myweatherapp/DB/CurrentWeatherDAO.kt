package com.example.myweatherapp.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDAO {

@Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCurrentWeatherData(currentDataList:WeatherDataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertWeatherDetails(details:DetailsEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeatherData(data:List<DailyWeatherEntity>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourlyForecastData(data:List<HourlyForecastEntity>)


    @Query("select * from WeatherDetails")
     fun getWeatherDetails():Flow<List<DetailsEntity>>




@Query("select * from currentweatherdata")
     fun getCurrentWeatherData():Flow<List<WeatherDataEntity>>

     @Query("Select COUNT(id) from currentweatherdata")
     suspend fun getCurrentCount():Int


    @Query("Select COUNT(id) from dailyweather")
    suspend fun getDailyCount():Int


    @Query("Select COUNT(id) from HourlyForecast")
    suspend fun getHourlyCount():Int




     @Query("select * from dailyweather")
     fun getDailyWeatherData():Flow<List<DailyWeatherEntity>>



     @Query("select * from HourlyForecast")
     fun getHourlyForecastData():Flow<List<HourlyForecastEntity>>

}