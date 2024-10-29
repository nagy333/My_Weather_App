package com.example.myweatherapp.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDAO {

@Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCurrentWeatherData(currentDataList:List<WeatherDataEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertWeatherDetails(details:DetailsEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeatherData(data:List<DailyWeatherEntity>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourlyForecastData(data:List<HourlyForecastEntity>)


    @Query("select * from WeatherDetails")
    suspend fun getWeatherDetails():DetailsEntity




@Query("select * from currentweatherdata")
     fun getCurrentWeatherData():Flow<List<WeatherDataEntity>>

     @Query("Select COUNT(id) from currentweatherdata")
     fun getCount():Flow<Int>




     @Query("select * from dailyweather")
     fun getDailyWeatherData():Flow<List<DailyWeatherEntity>>



     @Query("select * from HourlyForecast")
     fun getHourlyForecastData():Flow<List<HourlyForecastEntity>>

}