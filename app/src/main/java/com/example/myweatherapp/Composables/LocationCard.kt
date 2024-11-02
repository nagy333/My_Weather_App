package com.example.myweatherapp.Composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myweatherapp.UseCases.Indexx
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun MyLocationCard(
    city:String,
    pTemp:String,
    pWeatherCondition:String,
    index:Int,
    onLocationCardClicked:(Int)->Unit,
    onLocationCardClickedV:(Int)->Unit
){
    MyWeatherAppTheme {
        Card(
            modifier = Modifier.fillMaxWidth()
                .height(150.dp)
                .padding(8.dp)
                .clickable (onClick = { onLocationCardClicked(index)
                    onLocationCardClickedV(index)
                Indexx.index=index})
        , colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        , border = BorderStroke(4.dp,MaterialTheme.colorScheme.primary)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (cityName,temp,weatherCondition)=createRefs()
                InfoTextBold(city, fontSize = 35.sp,
                    modifier = Modifier.constrainAs(cityName){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom,20.dp)
                    start.linkTo(parent.start,20.dp)

                })
                InfoTextBold("$pTemp°C",
                    fontSize = 35.sp,
                    modifier = Modifier.constrainAs(temp){
                    top.linkTo(parent.top,10.dp)
                    end.linkTo(parent.end,20.dp)
                })
                InfoTextBold(pWeatherCondition,
                    fontSize = 35.sp,
                    modifier = Modifier.constrainAs(weatherCondition){
                    top.linkTo(temp.bottom,4.dp)
                    bottom.linkTo(parent.bottom,10.dp)
                    end.linkTo(parent.end,20.dp)
                })
            }
        }
    }

}
@Preview(showSystemUi = true)
@Composable
fun PPP(){
    MyWeatherAppTheme { //MyLocationCard("matay","20","Sunny",{})
     }

}