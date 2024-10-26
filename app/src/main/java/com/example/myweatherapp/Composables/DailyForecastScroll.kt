package com.example.myweatherapp.Composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.States.DailyCardInfoUiState

@Composable
fun DailyForecastScroll(data:List<DailyCardInfoUiState>,modifier: Modifier=Modifier){
    Column(modifier = modifier
        .fillMaxWidth()
        .height(250.dp)
        .padding(8.dp).verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            DailyForecastCard("today",
                max = data[0].maxTemp!!,
                min = data[0].minTemp!!,
                condition = data[0].weatherCondition!!,
                modifier=Modifier)
            DailyForecastCard(day = "tomorrow",
                max = data[1].maxTemp!!,
                min = data[1].minTemp!!,
                condition = data[1].weatherCondition!!,
                modifier=Modifier)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            DailyForecastCard(day = data[2].dayName!!,
                max = data[2].maxTemp!!,
                min = data[2].minTemp!!,
                condition = data[2].weatherCondition!!,
                modifier=Modifier)
            DailyForecastCard(day = data[3].dayName!!,
                max = data[3].maxTemp!!,
                min = data[3].minTemp!!,
                condition = data[3].weatherCondition!!,
                modifier=Modifier)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            DailyForecastCard(day = data[4].dayName!!,
                max = data[4].maxTemp!!,
                min = data[4].minTemp!!,
                condition = data[4].weatherCondition!!,
                modifier=Modifier)
            DailyForecastCard(day = data[5].dayName!!,
                max = data[5].maxTemp!!,
                min = data[5].minTemp!!,
                condition = data[5].weatherCondition!!,
                modifier=Modifier)
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun p5(){
DailyForecastScroll(emptyList())
}