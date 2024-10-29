package com.example.myweatherapp.DI

import android.content.Context
import androidx.compose.material3.rememberTopAppBarState
import com.example.myweatherapp.DB.CurrentWeatherDAO
import com.example.myweatherapp.DB.CurrentWeatherDatabase
import com.example.myweatherapp.Repo.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideCurrentWeatherRepo(currentDao:CurrentWeatherDAO,@ApplicationContext context:Context):WeatherRepo{
        return WeatherRepo(currentDao,context)
    }
    @Provides
    fun provideWeatherDao(@ApplicationContext context:Context):CurrentWeatherDAO{
        return CurrentWeatherDatabase.getInstance(context).dao()
    }
    @Provides
    fun porvideContext(@ApplicationContext context: Context):Context{
        return context
    }
}