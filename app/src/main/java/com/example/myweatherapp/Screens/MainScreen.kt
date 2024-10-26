package com.example.myweatherapp.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myweatherapp.Composables.DailyForecastScroll
import com.example.myweatherapp.Composables.InfoTextBold
import com.example.myweatherapp.Composables.InfoTextLight
import com.example.myweatherapp.R
import com.example.myweatherapp.States.ApiUiState
import com.example.myweatherapp.States.DailyCardInfoUiState
import com.example.myweatherapp.States.MainScreenUiState
import com.example.myweatherapp.UseCases.CheckConnectivity
import com.example.myweatherapp.ViewModels.MainScreenViewModel
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import kotlinx.coroutines.DelicateCoroutinesApi

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun MainScreen(navController: NavController){
    val viewModel:MainScreenViewModel= hiltViewModel()
    val state=viewModel.state.collectAsState()
    val apiState=viewModel.apiState.collectAsState()
    var old=viewModel.oldValue.collectAsState()
    val networkState = viewModel._newtworkState.collectAsState(initial = CheckConnectivity.Status.Unavailable)
    MyWeatherAppTheme {
        MainScreenContent(
            state =state, onMoreDetailsClicked ={ navController.navigate("DetailsScreen")},
            onRefresh = viewModel::getDailyWeatherData,
            apiState =apiState, networkState = networkState,
            oldValue = old, assignNewValue = viewModel::assignNewValue)


    }

}
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    state:State<MainScreenUiState?>,
    onMoreDetailsClicked:()->Unit, onRefresh:()->Unit,
    apiState:State<ApiUiState>,
    networkState:State<CheckConnectivity.Status>,
    oldValue:State<CheckConnectivity.Status>,
    assignNewValue:(CheckConnectivity.Status)->Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    MyWeatherAppTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
             LaunchedEffect(networkState.value) {
                 Log.d("mmm","old : ${oldValue.value} new : ${networkState.value}")
                 if (oldValue.value!=networkState.value) {
                     snackbarHostState.showSnackbar("${networkState.value}")


                 }
                 assignNewValue(networkState.value)
                 }
            },
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = state.value?.currentWeatherData?.dayName!!,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },

                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            var isRefreshing by remember { mutableStateOf(false) }
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    val (image, weatherConditionText,
                        cityName, monthText, dayText,
                        dateText, countryText,
                        timeText, divider,
                        maxTempText, loadingView,
                        maxTemp, minTemp,
                        scroll, verticalDivider, moreDetailsBtn) = createRefs()

                        Image(painter =
                        painterResource(loadImageBasedOnWeatherCondition(state.value?.currentWeatherData?.weatherCondition!!)),
                            contentDescription = "",
                            modifier = Modifier
                                .size(150.dp)
                                .constrainAs(image) {
                                    end.linkTo(parent.end)
                                    start.linkTo(parent.start)
                                })


                        InfoTextLight(
                            state.value?.currentWeatherData!!.weatherCondition!!,
                            modifier = Modifier.constrainAs(weatherConditionText) {
                                top.linkTo(image.bottom, 20.dp)
                                start.linkTo(image.start)
                                end.linkTo(image.end)
                            })


                        InfoTextBold(
                            state.value?.currentWeatherData!!.cityName!!,
                            fontSize = 40.sp,
                            modifier = Modifier.constrainAs(cityName) {
                                top.linkTo(image.top, 50.dp)
                                end.linkTo(parent.end)
                                start.linkTo(image.end)
                            })


                        InfoTextBold(state.value?.currentWeatherData!!.monthName!!, fontSize = 25.sp,
                            modifier = Modifier.constrainAs(monthText) {
                                top.linkTo(image.top, 50.dp)
                                start.linkTo(parent.start)
                                end.linkTo(image.start)
                            })


                        InfoTextBold(state.value?.currentWeatherData!!.dayNumber!!, fontSize = 50.sp,
                            modifier = Modifier.constrainAs(dayText) {
                                top.linkTo(monthText.top, 25.dp)
                                start.linkTo(parent.start)
                                end.linkTo(image.start)
                            })


                        InfoTextBold(state.value?.currentWeatherData!!.dateNumber!!,
                            fontSize = 28.sp,
                            modifier = Modifier.constrainAs(dateText) {
                                top.linkTo(dayText.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(image.start)
                            })


                        InfoTextLight(
                            state.value?.currentWeatherData!!.countryName!!,
                            modifier = Modifier.constrainAs(countryText) {
                                top.linkTo(cityName.bottom, 10.dp)
                                start.linkTo(image.end)
                                end.linkTo(parent.end)
                            })


                        InfoTextBold(
                            text = state.value?.currentWeatherData!!.time!!,
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


                        InfoTextBold("${state.value?.currentWeatherData!!.Temp}°C", fontSize = 60.sp,
                            modifier = Modifier.constrainAs(maxTemp) {
                                top.linkTo(maxTempText.bottom)
                                start.linkTo(maxTempText.start)
                            })


                        Button(
                            onClick = onMoreDetailsClicked,
                            modifier = Modifier.constrainAs(moreDetailsBtn) {
                                top.linkTo(maxTemp.bottom, 10.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                            Text(
                                "More Details",
                                color = MaterialTheme.colorScheme.background,
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 20.sp
                            )
                        }
                        DailyForecastScroll(
                            data = state.value?.dailyCardInfo!!,
                            modifier = Modifier.constrainAs(scroll) {
                                top.linkTo(moreDetailsBtn.bottom, 10.dp)
                            })

                   // }
                }
            }


        }

    }

}
@Preview(showSystemUi = true)
@Composable
fun p() {
    MyWeatherAppTheme {

    }

}
fun loadImageBasedOnWeatherCondition(condition:String): Int {
    return when(condition){
        "Clear sky"-> R.drawable.clear_sky

        "Few clouds","Overcast clouds","Broken clouds","Scattered clouds"-> R.drawable.cloudy

        "Fog","Freezing Fog"-> R.drawable.fog

        "Haze","Sand/dust","Smoke","Mist"-> R.drawable.haze

        "Light Drizzle","Drizzle","Heavy Drizzle"-> R.drawable.drizzle


        "Light Rain","Moderate Rain","Heavy Rain",
        "Freezing rain","Light shower rain","Shower rain","Heavy shower rain"-> R.drawable.rainy


        "Light snow","Snow","Heavy Snow","Snow shower","Heavy snow shower"-> R.drawable.snowy

        "Thunderstorm with light rain","Thunderstorm with rain","Thunderstorm with heavy rain"
            ,"Thunderstorm with light drizzle","Thunderstorm with drizzle"
            ,"Thunderstorm with heavy drizzle","Thunderstorm with Hail"-> R.drawable.thunder

        else -> {R.drawable.haze}
    }
}

