package com.example.myweatherapp.Composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myweatherapp.Screens.loadImageBasedOnWeatherCondition
import com.example.myweatherapp.States.DailyCardInfoUiState
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun MyDailyWeatherView(data:List<DailyCardInfoUiState>,modifier: Modifier=Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        DailyForecastCard(
            "today",
            max = data[0].maxTemp!!,
            min = data[0].minTemp!!,
            condition = data[0].weatherCondition!!,
            modifier = Modifier
        )
        DailyForecastCard(
            day = "tomorrow",
            max = data[1].maxTemp!!,
            min = data[1].minTemp!!,
            condition = data[1].weatherCondition!!,
            modifier = Modifier
        )

        DailyForecastCard(
            day = data[2].dayName!!,
            max = data[2].maxTemp!!,
            min = data[2].minTemp!!,
            condition = data[2].weatherCondition!!,
            modifier = Modifier
        )
        DailyForecastCard(
            day = data[3].dayName!!,
            max = data[3].maxTemp!!,
            min = data[3].minTemp!!,
            condition = data[3].weatherCondition!!,
            modifier = Modifier
        )

        DailyForecastCard(
            day = data[4].dayName!!,
            max = data[4].maxTemp!!,
            min = data[4].minTemp!!,
            condition = data[4].weatherCondition!!,
            modifier = Modifier
        )
        DailyForecastCard(
            day = data[5].dayName!!,
            max = data[5].maxTemp!!,
            min = data[5].minTemp!!,
            condition = data[5].weatherCondition!!,
            modifier = Modifier
        )
    }
}

@Composable
fun DailyForecastCard(day:String,max:String,min:String,condition:String,modifier: Modifier){
    MyWeatherAppTheme {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp),
            border = BorderStroke(4.dp,MaterialTheme.colorScheme.background),
            colors = CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                val(dayText,image,
                    weatherConditionText,
                    maxTemp,minTemp)=createRefs()

                CardInfoText(day,
                    fontSize = 20.sp,
                    modifier = Modifier.constrainAs(dayText){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start,10.dp)
                        bottom.linkTo(parent.bottom)
                }.width(80.dp))
                Image(painter = painterResource(loadImageBasedOnWeatherCondition(condition)),
                    contentDescription = "",
                    modifier = Modifier.constrainAs(image){
                        top.linkTo(parent.top)
                        start.linkTo(dayText.end,10.dp)
                        bottom.linkTo(parent.bottom)

                    }.size(40.dp))
                CardInfoText(condition,
                    fontSize = 20.sp,
                    modifier = Modifier.
                    constrainAs(weatherConditionText){
                        top.linkTo(parent.top)
                        start.linkTo(image.end,10.dp)
                        bottom.linkTo(parent.bottom)
                    })

                CardInfoText(" $min°C",
                    fontSize = 20.sp,
                    modifier=Modifier.constrainAs(minTemp){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end,10.dp)
                        bottom.linkTo(parent.bottom)
                })
                CardInfoText("$max°C /",
                    fontSize = 20.sp,
                    modifier = Modifier.constrainAs(maxTemp){
                        end.linkTo(minTemp.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    })
            }
        }
    }
}

@Composable
fun CardInfoText(text:String,modifier: Modifier=Modifier,fontSize: TextUnit =30.sp){
    MyWeatherAppTheme {
        Text(text=text,
        modifier=modifier,
        style = MaterialTheme.typography.bodyLarge,
        fontSize = fontSize,
        color = MaterialTheme.colorScheme.background,

    )
    }

}

@Preview(showSystemUi = true)
@Composable
fun p5(){
MyDailyWeatherView(emptyList())
}