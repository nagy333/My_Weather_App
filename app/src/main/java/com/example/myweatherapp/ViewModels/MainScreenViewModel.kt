package com.example.myweatherapp.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.Parsing.parseCurrentDataFromDBtoUiState
import com.example.myweatherapp.Parsing.parseDailyWeatherFromEntityToUiState
import com.example.myweatherapp.Repo.WeatherRepo
import com.example.myweatherapp.States.DailyCardInfoUiState
import com.example.myweatherapp.States.MainScreenUiState
import com.example.myweatherapp.UseCases.CheckConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel@Inject constructor(
    private val repo: WeatherRepo
):ViewModel() {

    private val _state= MutableStateFlow(MainScreenUiState())
    val state=_state.asStateFlow()
    val apiState=repo.apiState

val _newtworkState=repo.networkState

    var oldValue=MutableStateFlow(CheckConnectivity.Status.Available)
    fun assignNewValue(newValue:CheckConnectivity.Status){
        oldValue.update { newValue }
    }


    val textState=MutableStateFlow("Nagy")

    init {
                 updateData()
        getDailyWeatherData()
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun getDailyWeatherData(){
        GlobalScope.launch {
            repo.getDailyWeatherData().collect{data->
                 val  dailyData= parseDailyWeatherFromEntityToUiState(data)
                _state.update { it.copy(dailyCardInfo = dailyData) }
            }
        }
    }


    private fun updateData() {
        viewModelScope.launch {
            repo.getCurrentWeatherData().collect{data->
                Log.d("list",data.size.toString())
                val currentWeather= parseCurrentDataFromDBtoUiState(data[0])
                _state.update { it.copy(currentWeatherData = currentWeather) }
            }
            }
    }

}