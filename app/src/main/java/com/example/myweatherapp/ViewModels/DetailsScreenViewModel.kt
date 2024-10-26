package com.example.myweatherapp.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myweatherapp.Parsing.parseDetailsDataFromDbToUiState
import com.example.myweatherapp.Parsing.parseHourlyDataFromDBToUiState
import com.example.myweatherapp.Repo.WeatherRepo
import com.example.myweatherapp.States.DetailsCardUiState
import com.example.myweatherapp.States.DetailsScreenUiState
import com.example.myweatherapp.States.HourlyCardUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel@Inject constructor(
    private val repo: WeatherRepo
):ViewModel() {

    private val _state= MutableStateFlow(DetailsScreenUiState())
    var state=_state.asStateFlow()


    init {
        getWeatherDetails()
        getHourlyForecastData()
    }

    private fun getHourlyForecastData() {
        viewModelScope.launch {
            repo.getHourlyForecastData().collect{data->
               val list= parseHourlyDataFromDBToUiState(data)
               _state.update { it.copy(hourlyCardInfo = list) }
                Log.d("hourly",data.size.toString())
            }
        }
    }

    fun getWeatherDetails() {
        viewModelScope.launch {
           val details= repo.getWeatherDetails()

            val detailsCardData= parseDetailsDataFromDbToUiState(details)
            _state.update{it.copy(detailsCardInfo = detailsCardData)}
        }
    }
}