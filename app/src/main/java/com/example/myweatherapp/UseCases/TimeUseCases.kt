package com.example.myweatherapp.UseCases

import android.annotation.SuppressLint
import android.icu.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date

object TimeUseCases {
    fun getHourlyTime(time:String):String {
        val nTime=time.toInt()+3
        val t=nTime.toString()
        return when(t){
            "13"->"01 pm"
            "14"->"02 pm"
            "15"->"03 pm"
            "16"->"04 pm"
            "17"->"05 pm"
            "18"->"06 pm"
            "19"->"07 pm"
            "20"->"08 pm"
            "21"->"09 pm"
            "22"->"10 pm"
            "23"->"11 pm"
            "24"->"12 am"
            "25"->"01 am"
            "26"->"02 am"
            "3"->"03 am"
            "4"->"04 am"
            "5"->"05 am"
            "6"->"06 am"
            "7"->"07 am"
            "8"->"08 am"
            "9"->"09 am"
            "10"->"10 am"
            "11"->"11 am"
            "12"->"12 pm"
            else -> {"20 pm"}
        }
    }


    fun convertNumberToMonth(num:String): String {
        var month=""
        when(num){
            "1"-> month= "January"
            "2"-> month= "February"
            "3"-> month= "March"
            "4"-> month= "April"
            "5"-> month= "May"
            "6"-> month= "June"
            "7"-> month= "July"
            "8"-> month= "August"
            "9"-> month= "September"
            "10"-> month= "October"
            "11"-> month= "November"
            "12"-> month= "December"
        }
        return month
    }
    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime():String{
        val sdf= SimpleDateFormat("h:mm aa")
        val cuurentTime=sdf.format(Date())
        return cuurentTime
    }
    @SuppressLint("SimpleDateFormat")
    fun getDayName(stringDate:String):String{
        val date= SimpleDateFormat("yyy-MM-dd").parse(stringDate.substring(0,10))
        val cal= Calendar.getInstance()
        cal.time = date
        val day= android.text.format.DateFormat.format("EEEE",date);
        return day.toString()
    }
    fun getSunsetTime(time:String):String{
        val t = time.substring(0, 2)
        val n = t.toInt() + 3
        return when (n) {
            16 -> "04${time.substring(2, 5)}"
            17 -> "05${time.substring(2, 5)}"
            18 -> "06${time.substring(2, 5)}"
            19 -> "07${time.substring(2, 5)}"
            else -> {
                "6:00"
            }
        }

    }
    fun getSunriseTime(time:String):String{
        val t = time.substring(0, 2)
        val n = t.toInt() + 3
        return when (n) {
            5 -> "05${time.substring(2, 5)}"
            6 -> "06${time.substring(2, 5)}"
            7 -> "07${time.substring(2, 5)}"
            8 -> "08${time.substring(2, 5)}"
            else -> {
                "6:00"
            }
        }

    }
}