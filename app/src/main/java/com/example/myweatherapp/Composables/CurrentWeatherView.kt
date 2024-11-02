package com.example.myweatherapp.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myweatherapp.Screens.loadImageBasedOnWeatherCondition
import com.example.myweatherapp.States.CurrentWeatherUiState
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun MyCurrentWeatherView(
    data:CurrentWeatherUiState,
    ){
    MyWeatherAppTheme {

        ConstraintLayout(
            modifier = Modifier
                .padding(8.dp)
                .wrapContentHeight()
        ) {
            val (image, weatherConditionText,
                cityName, monthText, dayText,
                dateText, countryText,
                timeText, divider,
                maxTempText, maxTemp) = createRefs()

            Image(painter =
            painterResource(loadImageBasedOnWeatherCondition(data.weatherCondition!!)),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
                    .constrainAs(image) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    })


            InfoTextLight(
                data.weatherCondition,
                modifier = Modifier.constrainAs(weatherConditionText) {
                    top.linkTo(image.bottom, 20.dp)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                })


            InfoTextBold(
                data.dayName!!,
                fontSize = 40.sp,
                modifier = Modifier.constrainAs(cityName) {
                    top.linkTo(image.top, 50.dp)
                    end.linkTo(parent.end)
                    start.linkTo(image.end)
                })

            InfoTextBold(data.monthName!!,
                fontSize = 25.sp,
                modifier = Modifier.constrainAs(monthText) {
                    top.linkTo(image.top, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(image.start)
                })


            InfoTextBold(data.dayNumber!!,
                fontSize = 50.sp,
                modifier = Modifier.constrainAs(dayText) {
                    top.linkTo(monthText.top, 25.dp)
                    start.linkTo(parent.start)
                    end.linkTo(image.start)
                })


            InfoTextBold(data.dateNumber!!,
                fontSize = 28.sp,
                modifier = Modifier.constrainAs(dateText) {
                    top.linkTo(dayText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(image.start)
                })


            InfoTextLight(
                data.countryName!!,
                modifier = Modifier.constrainAs(countryText) {
                    top.linkTo(cityName.bottom, 10.dp)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                })


            InfoTextBold(
                text = data.time!!,
                fontSize = 28.sp,
                modifier = Modifier.constrainAs(timeText) {
                    top.linkTo(dateText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(image.start)
                })


            HorizontalDivider(color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .width(160.dp)
                    .constrainAs(divider) {
                        top.linkTo(weatherConditionText.bottom)
                        start.linkTo(image.start)
                        end.linkTo(cityName.start)
                    })


            InfoTextLight("Temp", modifier = Modifier
                .constrainAs(maxTempText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(divider.bottom, 20.dp)
                })


            InfoTextBold("${data.Temp}°C",
                fontSize = 60.sp,
                modifier = Modifier.constrainAs(maxTemp) {
                    top.linkTo(maxTempText.bottom)
                    start.linkTo(maxTempText.start)
                })
        }
    }
}