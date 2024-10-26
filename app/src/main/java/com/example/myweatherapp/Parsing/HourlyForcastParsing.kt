package com.example.myweatherapp.Parsing

import com.example.myweatherapp.DB.HourlyForecastEntity
import com.example.myweatherapp.Model.HourluForecastModel
import com.example.myweatherapp.States.HourlyCardUIState
import com.example.myweatherapp.UseCases.TimeUseCases
import retrofit2.Response


fun parseHourlyDataFromDBToUiState(list:List<HourlyForecastEntity>):ArrayList<HourlyCardUIState>{
    val newList:ArrayList<HourlyCardUIState> = ArrayList()
    list.forEach {
       val item= HourlyCardUIState(time = it.time, temp = it.temp)
        newList.add(item)
    }
    return newList
}

fun parseHourlyDataFromApiToDB(response: Response<HourluForecastModel>):List<HourlyForecastEntity>{
    val hourlyList:ArrayList<HourlyForecastEntity> = ArrayList()
    for (i:Int in 0..23){
        val item=HourlyForecastEntity(
            id=i,
            time = TimeUseCases.getHourlyTime(response.body()!!.data!![i].datetime!!.substring(11,13)),
            temp = response.body()!!.data!![i].temp!!.toInt().toString()
        )
        hourlyList.add(item)

    }
    return hourlyList

}