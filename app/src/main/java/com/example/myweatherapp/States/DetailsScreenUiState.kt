package com.example.myweatherapp.States

data class DetailsScreenUiState(
    var hourlyCardInfo : List<HourlyCardUIState>? = initHourlyCard(),
    val detailsCardInfo : DetailsCardUiState? = DetailsCardUiState()
)
fun initHourlyCard():List<HourlyCardUIState>{
    val list=ArrayList<HourlyCardUIState>()
    for (i:Int in 0..23){
        list.add(HourlyCardUIState())
    }
    return list
}

