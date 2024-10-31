package com.example.myweatherapp.Parsing

import com.example.myweatherapp.DB.HourlyForecastEntity
import com.example.myweatherapp.Model.HourluForecastModel
import com.example.myweatherapp.States.HourlyCardUIState
import com.example.myweatherapp.UseCases.TimeUseCases
import retrofit2.Response


fun parseHourlyDataFromDBToUiState(list:List<HourlyForecastEntity>):ArrayList<HourlyCardUIState>{
    val newList:ArrayList<HourlyCardUIState> = ArrayList()
    for (i in 0..23) {
            val item = HourlyCardUIState(time = list[i].time, temp = list[i].temp)
            newList.add(item)
    }
    return newList
}

fun parseHourlyDataFromApiToDB(response: Response<HourluForecastModel>,count:Int):List<HourlyForecastEntity>{
    val hourlyList:ArrayList<HourlyForecastEntity> = ArrayList()
    var c = count
    for (i:Int in 0..23){
        val item=HourlyForecastEntity(
            id=c,
            time = TimeUseCases.getHourlyTime(response.body()!!.data!![i].datetime!!.substring(11,13)),
            temp = response.body()!!.data!![i].temp!!.toInt().toString()
        )
        c++
        hourlyList.add(item)

    }
    return hourlyList

}