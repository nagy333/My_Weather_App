package com.example.myweatherapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myweatherapp.Composables.HourlyForecastCard
import com.example.myweatherapp.Composables.InfoTextBold
import com.example.myweatherapp.Composables.InfoTextLight
import com.example.myweatherapp.Composables.WeatherDetailsCard
import com.example.myweatherapp.States.DetailsCardUiState
import com.example.myweatherapp.States.DetailsScreenUiState
import com.example.myweatherapp.States.HourlyCardUIState
import com.example.myweatherapp.ViewModels.DetailsScreenViewModel
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import kotlinx.coroutines.delay

@Composable
fun DetailsScreen(navController: NavController){
    val viewModel:DetailsScreenViewModel= hiltViewModel()
    val stat=viewModel.state.collectAsState()
    DetailsScreenContent(onBackClicked = { navController.navigate("MainScreen") }
        ,state=stat)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContent(
    onBackClicked:()->Unit
    ,state:State<DetailsScreenUiState>, ){
    MyWeatherAppTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = {
                    InfoTextBold("Weather Details", Modifier.padding(8.dp)) },
                    colors = TopAppBarDefaults
                        .centerAlignedTopAppBarColors(containerColor
                        = MaterialTheme.colorScheme.background),
                    navigationIcon = {
                        IconButton(onClick = onBackClicked) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "", tint =MaterialTheme.colorScheme.primary )
                        }
                    }, modifier = Modifier.padding(top = 8.dp))
            }
        ) {innerPadding->
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)) {
                val (lazyRow,windCard,
                    feelsLikeCard,humidityCard,
                    airPressureCard,sunriseCard,sunsetCard)=createRefs()


                LazyRow(contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.constrainAs(lazyRow){
                        top.linkTo(parent.top,30.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    items(state.value.hourlyCardInfo!!){
                        HourlyForecastCard(it,
                            sunrise = state.value.detailsCardInfo?.sunrise!!,
                            sunset = state.value.detailsCardInfo?.sunset!!)
                    }
                }

                WeatherDetailsCard("FeelsLike"
                    , "${state.value.detailsCardInfo!!}°c",
                    modifier =Modifier.constrainAs(feelsLikeCard){
                        top.linkTo(lazyRow.bottom,20.dp)
                        start.linkTo(parent.start,20.dp)
                    })

                WeatherDetailsCard("Humidity",
                    "${state.value.detailsCardInfo?.humidity}%",
                    modifier = Modifier.constrainAs(humidityCard){
                        top.linkTo(feelsLikeCard.top)
                        end.linkTo(parent.end,20.dp)
                    })

                WeatherDetailsCard("NNw wind",
                    "${state.value.detailsCardInfo?.wind} km/h",
                    modifier = Modifier.constrainAs(windCard){
                        top.linkTo(feelsLikeCard.bottom,20.dp)
                        start.linkTo(feelsLikeCard.start)
                    })

                WeatherDetailsCard("Air Pressure",
                    "${state.value.detailsCardInfo?.airPres} hPa",
                    fontSize = 30.sp,
                    modifier = Modifier.constrainAs(airPressureCard){
                        top.linkTo(windCard.top)
                        end.linkTo(humidityCard.end)
                    })

                WeatherDetailsCard("Sunrise",
                    "${state.value.detailsCardInfo?.sunrise} am",
                    modifier = Modifier.constrainAs(sunriseCard){
                        top.linkTo(windCard.bottom,20.dp)
                        start.linkTo(windCard.start)
                    })

                WeatherDetailsCard("Sunset",
                    "${state.value.detailsCardInfo?.sunset} pm",
                    modifier = Modifier.constrainAs(sunsetCard){
                        top.linkTo(sunriseCard.top)
                        end.linkTo(airPressureCard.end)
                    })
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun p3(){
    MyWeatherAppTheme {
        //DetailsScreenContent({})
    }
}