package com.example.myweatherapp.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [
    WeatherDataEntity::class,
    DetailsEntity::class,
    DailyWeatherEntity::class,
    HourlyForecastEntity::class],
    version = 1, exportSchema = false)
abstract class CurrentWeatherDatabase():RoomDatabase() {
abstract fun dao():CurrentWeatherDAO
companion object{
    private  var INSTANCE:CurrentWeatherDatabase?=null
    fun getInstance(context: Context):CurrentWeatherDatabase{
        synchronized(this){
            if (INSTANCE==null)
                INSTANCE= buildDatabase(context)
            return INSTANCE!!
        }

    }

    private fun buildDatabase(context: Context):CurrentWeatherDatabase{
        return Room.databaseBuilder(context,CurrentWeatherDatabase::class.java,"Current Weather Database").build()
}
}
}