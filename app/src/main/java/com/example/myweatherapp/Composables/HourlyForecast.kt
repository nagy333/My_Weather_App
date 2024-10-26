package com.example.myweatherapp.Composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.States.HourlyCardUIState
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun HourlyForecastCard(data:HourlyCardUIState,sunrise:String,sunset:String){
    MyWeatherAppTheme {  Card(modifier = Modifier
        .padding(8.dp)
        .width(100.dp)
        .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
       ) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)
            , horizontalAlignment = Alignment.CenterHorizontally) {
           CardInfoText("${data.time}")
            Image(painter = painterResource(loadImageBasedOnSun(sunrise,sunset, time = data.time!!)), contentDescription = "")
            Log.d("nnn", data.time)
            Spacer(Modifier.height(8.dp))
            CardInfoText("${data.temp}°c")
        }
    } }

}
@Preview(showSystemUi = true)
@Composable
fun pp(){
   // MyWeatherAppTheme {HourlyForecastCard()  }

}
fun loadImageBasedOnSun(sunrise: String,sunset: String,time:String):Int{
    val timeStamb=time.substring(3)
    val t=time.substring(0,2).toInt()
    return if (t==12&&timeStamb=="am") R.drawable.moon
    else if (t==12&&timeStamb=="pm") R.drawable.day
    else if (t>sunset.substring(0,2).toInt()&&timeStamb=="pm"||
        t<sunrise.substring(0,2).toInt()&&timeStamb=="am") R.drawable.moon else R.drawable.day
}