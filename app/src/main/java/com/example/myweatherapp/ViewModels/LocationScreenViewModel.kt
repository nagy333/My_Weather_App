package com.example.myweatherapp.ViewModels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.DB.CurrentWeatherDAO
import com.example.myweatherapp.Parsing.parseLocationDataFromDbToUiState
import com.example.myweatherapp.Repo.WeatherRepo
import com.example.myweatherapp.States.LocationScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel @SuppressLint("StaticFieldLeak")
@Inject constructor(
    val repo: WeatherRepo,
    val weatherDAO: CurrentWeatherDAO,
):ViewModel() {
    init {
        getLocationsList()
    }

    private val _state= MutableStateFlow(LocationScreenUiState("", emptyList()))
    val state=_state.asStateFlow()


    fun updateValue(newValue:String){
        _state.update { it.copy(searchText = newValue) }
    }
    fun onSearchClicked(){
            repo.storeLocationWeather(
                currentDao = weatherDAO,
                cityName = _state.value.searchText
                )

        repo.storeDailyWeatherInDatabase(
            weatherDAO,
            1,
            _state.value.searchText)

        repo.storeHourlyForecastInDatabase(
            weatherDAO,
            1,
            _state.value.searchText)

        _state.value.searchText=""
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getLocationsList(){
        GlobalScope.launch {repo.getCurrentWeatherData().collect{data->
            val list= parseLocationDataFromDbToUiState(data)
            _state.update { it.copy(locationList = list) }
        }  }
    }
}