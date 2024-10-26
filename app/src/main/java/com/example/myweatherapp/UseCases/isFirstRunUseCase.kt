package com.example.myweatherapp.UseCases

import android.content.Context
import android.content.SharedPreferences
import com.example.myweatherapp.R
import com.example.myweatherapp.States.CurrentWeatherUiState
import com.example.myweatherapp.States.DailyCardInfoUiState
import com.example.myweatherapp.States.DetailsCardUiState
import com.example.myweatherapp.States.HourlyCardUIState
import com.example.myweatherapp.States.MainScreenUiState

object isFirstRunUseCase {
    private var sharedPrefs: SharedPreferences?=null
    private const val SHARED_PREFS_NAME ="My Shared Prefs"
    private const val IS_FIRST_LAUNCHED_KEY="key"
    fun  initSharedPrefs(context: Context){
        sharedPrefs= context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }
    var isFirstLaunched:Boolean?
        get() = sharedPrefs?.getBoolean(IS_FIRST_LAUNCHED_KEY,true)
        set(value) = sharedPrefs?.edit()?.putBoolean(IS_FIRST_LAUNCHED_KEY, value!!)?.apply()!!


}

