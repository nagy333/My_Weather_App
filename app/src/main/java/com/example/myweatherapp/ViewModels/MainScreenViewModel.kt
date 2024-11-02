package com.example.myweatherapp.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.Parsing.parseCurrentDataFromDBtoUiState
import com.example.myweatherapp.Parsing.parseDailyWeatherFromEntityToUiState
import com.example.myweatherapp.Parsing.parseDetailsDataFromDbToUiState
import com.example.myweatherapp.Parsing.parseHourlyDataFromDBToUiState
import com.example.myweatherapp.Repo.WeatherRepo
import com.example.myweatherapp.States.DailyCardInfoUiState
import com.example.myweatherapp.States.MainScreenUiState
import com.example.myweatherapp.UseCases.CheckConnectivity
import com.example.myweatherapp.UseCases.Indexx
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
    private val repo: WeatherRepo,
):ViewModel() {


    private val _state= MutableStateFlow(MainScreenUiState())
    val state=_state.asStateFlow()

    var Index = MutableStateFlow(0)

    val apiState=repo.apiState

val _newtworkState=repo.networkState

    var oldValue=MutableStateFlow(CheckConnectivity.Status.Available)
    fun assignNewValue(newValue:CheckConnectivity.Status){
        oldValue.update { newValue }
    }


    val textState=MutableStateFlow("Nagy")

    init {
        Log.d("Index",Index.value.toString())
        updateData()

        getDailyWeatherData()

        getHourlyForecastData()

        getWeatherDetails()
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
                try {
                    val currentWeather= parseCurrentDataFromDBtoUiState(data[Index.value])
                    _state.update { it.copy(currentWeatherData = currentWeather) }
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }
            }
    }


    private fun getHourlyForecastData() {
        viewModelScope.launch {
            repo.getHourlyForecastData().collect{data->
                val list= parseHourlyDataFromDBToUiState(data)
                _state.update { it.copy(hourlyWeather = list) }
                Log.d("hourly",data.size.toString())
            }
        }
    }


    fun getWeatherDetails() {
        viewModelScope.launch {
            repo.getWeatherDetails().collect{data->
                val detailsCardData= parseDetailsDataFromDbToUiState(data[0])
                _state.update{it.copy(detailsCardInfo = detailsCardData)}
            }

        }
    }
    fun updateIndex(index:Int){
        Index.update { index }
        Log.d("index",Index.value.toString())

    }

}