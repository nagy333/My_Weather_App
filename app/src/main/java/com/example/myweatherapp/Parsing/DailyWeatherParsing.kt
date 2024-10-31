package com.example.myweatherapp.Parsing

import com.example.myweatherapp.DB.DailyWeatherEntity
import com.example.myweatherapp.Model.DailyWeatherModel
import com.example.myweatherapp.States.DailyCardInfoUiState
import com.example.myweatherapp.UseCases.TimeUseCases
import retrofit2.Response

fun parseDailyWeatherFromEntityToUiState(data:List<DailyWeatherEntity>): ArrayList<DailyCardInfoUiState> {
    val list:ArrayList<DailyCardInfoUiState> =ArrayList()
    data.forEach{
        list.add(DailyCardInfoUiState(
            dayName = it.day,
            weatherCondition = it.weatherCondition,
            maxTemp = it.maxTemp,
            minTemp = it.minTemp,
        ))
    }
    return list
}
fun parseDailyWeatherFromApiToDB(response: Response<DailyWeatherModel>,count:Int):List<DailyWeatherEntity>{
    val dataList:ArrayList<DailyWeatherEntity> = ArrayList()
    var c=count
    for (i:Int in 0..5){
        val weatherData=DailyWeatherEntity(
            id=c,
            day = TimeUseCases.getDayName(response.body()!!.data?.get(i)!!.datetime!!),
            weatherCondition = response.body()!!.data?.get(i)!!.weather!!.description!!,
            maxTemp = response.body()!!.data?.get(i)!!.maxTemp!!.toInt().toString(),
            minTemp = response.body()!!.data?.get(i)!!.minTemp!!.toInt().toString(),
        )
        c++
        dataList.add(weatherData)
    }
    return dataList

}