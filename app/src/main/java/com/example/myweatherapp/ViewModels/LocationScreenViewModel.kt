package com.example.myweatherapp.ViewModels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.DB.CurrentWeatherDAO
import com.example.myweatherapp.Repo.WeatherRepo
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
    val context: Context
):ViewModel() {

    private val _state= MutableStateFlow("")
    val state=_state.asStateFlow()


    fun updateValue(newValue:String){
        _state.update { newValue }
    }
    fun onSearchClicked(){

            repo.storeCurrentWeatherInDatabase(currentDao = weatherDAO,
                cityName = _state.value, context = context
                )

    }
}