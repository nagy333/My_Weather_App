package com.example.myweatherapp.Composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun WeatherDetailsCard(lightText:String,boldText:String,modifier: Modifier=Modifier,fontSize:TextUnit=40.sp){
    MyWeatherAppTheme {
        Card(modifier = modifier.width(150.dp).height(120.dp),
            border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary)) {
            ConstraintLayout(modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize().
                padding(8.dp)) {
                val (light,bold)=createRefs()
                InfoTextLight(lightText, fontSize = 30.sp, modifier = Modifier.constrainAs(light){
                    top.linkTo(parent.top,10.dp)
                    start.linkTo(parent.start,10.dp)
                })

                InfoTextBold(boldText, fontSize = fontSize, modifier = Modifier.constrainAs(bold){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(light.start)

                })
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun P5(){
    MyWeatherAppTheme {
        WeatherDetailsCard("FeelsLike","15 km/h")
    }
}