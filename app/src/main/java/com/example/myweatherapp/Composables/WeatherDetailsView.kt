package com.example.myweatherapp.Composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myweatherapp.States.DetailsCardUiState
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun MyWeatherDetailsView(details:DetailsCardUiState){

    MyWeatherAppTheme {
        ConstraintLayout(modifier = Modifier.wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp)) {
            val (windCard, feelsLikeCard,humidityCard, airPressureCard,sunriseCard,sunsetCard)=createRefs()


            WeatherDetailsCard("FeelsLike"
                , "${details.feelsLike!!}°c",
                modifier = Modifier.constrainAs(feelsLikeCard){
                    top.linkTo(parent.top,20.dp)
                    start.linkTo(parent.start)
                })



            WeatherDetailsCard("Humidity",
                "${details.humidity}%",
                modifier = Modifier.constrainAs(humidityCard){
                    top.linkTo(feelsLikeCard.top)
                    end.linkTo(parent.end)
                })

            WeatherDetailsCard("NNw wind",
                "${details.wind} km/h",
                modifier = Modifier.constrainAs(windCard){
                    top.linkTo(feelsLikeCard.bottom,20.dp)
                    start.linkTo(feelsLikeCard.start)
                })

            WeatherDetailsCard("Air Pressure",
                "${details.airPres} hPa",
                fontSize = 30.sp,
                modifier = Modifier.constrainAs(airPressureCard){
                    top.linkTo(windCard.top)
                    end.linkTo(humidityCard.end)
                })

            WeatherDetailsCard("Sunrise",
                "${details.sunrise} am",
                modifier = Modifier.constrainAs(sunriseCard){
                    top.linkTo(windCard.bottom,20.dp)
                    start.linkTo(windCard.start)
                })

            WeatherDetailsCard("Sunset",
                "${details.sunset} pm",
                modifier = Modifier.constrainAs(sunsetCard){
                    top.linkTo(sunriseCard.top)
                    end.linkTo(airPressureCard.end)
                })
        }
    }
}


@Composable
fun WeatherDetailsCard(lightText:String,boldText:String,modifier: Modifier=Modifier,fontSize: TextUnit =40.sp){
    MyWeatherAppTheme {
        Card(modifier = modifier.width(150.dp).height(120.dp),
            border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary)
        ) {
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

