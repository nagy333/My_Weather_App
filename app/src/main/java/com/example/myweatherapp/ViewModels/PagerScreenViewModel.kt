package com.example.myweatherapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myweatherapp.Repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagerScreenViewModel@Inject constructor(
    val repo: WeatherRepo
):ViewModel() {

   private val _state = MutableStateFlow(0)
    val state = _state.asStateFlow()
    private val _stateN = MutableStateFlow(0)
    val Nstate = _stateN.asStateFlow()
    init {
        getItemCount()
    }

    fun getItemCount(){
        viewModelScope.launch {
            val count=repo.getCurrentWeatherCount()
            _state.update { count }
        }
    }
    fun getCurrentData(){
        viewModelScope.launch {
            repo.getCurrentWeatherData().collect{data->

            }
        }
    }
}