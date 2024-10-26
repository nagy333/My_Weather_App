package com.example.myweatherapp.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myweatherapp.R
import com.example.myweatherapp.States.DailyCardInfoUiState
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun DailyForecastCard(day:String,max:String,min:String,condition:String,modifier: Modifier){
    MyWeatherAppTheme {
        Card(
            modifier = modifier
                .width(170.dp)
                .height(130.dp)
                .padding(8.dp),
            colors = CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                val(dayText,image,
                    weatherConditionText,maxTemp,minTemp)=createRefs()
                CardInfoText(day, fontSize = 25.sp, modifier = Modifier.constrainAs(dayText){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
                Image(painter = painterResource(R.drawable.sun),
                    contentDescription = "", modifier = Modifier.constrainAs(image){
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }.size(40.dp))
                CardInfoText(condition, fontSize = 25.sp,
                    modifier = Modifier.
                constrainAs(weatherConditionText){
                    bottom.linkTo(parent.bottom,10.dp)
                    start.linkTo(parent.start)
                })
                CardInfoText("$max°C ", fontSize = 25.sp,
                    modifier = Modifier.constrainAs(maxTemp){
                        end.linkTo(parent.end)
                        top.linkTo(image.bottom)
                })
                CardInfoText("$min°C", fontSize = 25.sp, modifier=Modifier.constrainAs(minTemp){
                    top.linkTo(maxTemp.bottom)
                    end.linkTo(parent.end)
                })
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun p1(){
    MyWeatherAppTheme { DailyForecastCard("TuesDay","35","20","sunny",Modifier)  }

}