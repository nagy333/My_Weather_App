package com.example.myweatherapp.ViewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LocationScreenViewModel@Inject constructor():ViewModel() {

    private val _state= MutableStateFlow("")
    val state=_state.asStateFlow()

    fun updateValue(newValue:String){
        _state.update { newValue }
    }
    fun onSearchClicked(){

    }
}