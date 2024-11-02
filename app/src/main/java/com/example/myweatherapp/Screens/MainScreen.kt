package com.example.myweatherapp.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myweatherapp.Composables.HourlyForecastCard
import com.example.myweatherapp.Composables.MyCurrentWeatherView
import com.example.myweatherapp.Composables.MyDailyWeatherView
import com.example.myweatherapp.Composables.MyLoadingView
import com.example.myweatherapp.Composables.MyMainScreenActionBar
import com.example.myweatherapp.Composables.MyWeatherDetailsView
import com.example.myweatherapp.R
import com.example.myweatherapp.States.ApiUiState
import com.example.myweatherapp.States.MainScreenUiState
import com.example.myweatherapp.UseCases.CheckConnectivity
import com.example.myweatherapp.ViewModels.MainScreenViewModel
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(navController: NavController,index:Int){
    val viewModel:MainScreenViewModel= hiltViewModel()
    val state=viewModel.state.collectAsState()
    val apiState=viewModel.apiState.collectAsState()
    var old=viewModel.oldValue.collectAsState()
    val networkState = viewModel._newtworkState.collectAsState(initial = CheckConnectivity.Status.Unavailable)
    MyWeatherAppTheme {
        MainScreenContent(
            state =state,
            onRefresh = viewModel::getDailyWeatherData,
            apiState =apiState, networkState = networkState,
            onAddClicked = { navController.navigate("LocationScreen") })
    }

}
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    state:State<MainScreenUiState?>,
    onRefresh:()->Unit,
    apiState:State<ApiUiState>,
    networkState:State<CheckConnectivity.Status>,
    onAddClicked:()->Unit
) {
    MyWeatherAppTheme {
        if (apiState.value.isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                MyLoadingView()
            }
        }
        else if (apiState.value.isSuccessful||
            networkState.value==CheckConnectivity.Status.Unavailable||
            networkState.value==CheckConnectivity.Status.Lost) {
            Scaffold(modifier = Modifier
                .fillMaxSize(),
                topBar = {
                    MyMainScreenActionBar(
                        state.value?.currentWeatherData!!.cityName!!,
                        onAddClicked)
                }
            ) { innerPadding ->
                val isRefreshing by remember { mutableStateOf(false) }
                PullToRefreshBox(
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh
                ) {
                    Column(modifier = Modifier.wrapContentHeight().
                    padding(innerPadding)
                        .verticalScroll(rememberScrollState())) {


                        MyCurrentWeatherView(
                            state.value!!.currentWeatherData!!)


                        LazyRow(contentPadding = PaddingValues(8.dp)) {
                            items(state.value!!.hourlyWeather){
                                HourlyForecastCard(it,
                                    sunrise = state.value!!.detailsCardInfo?.sunrise!!,
                                    sunset = state.value!!.detailsCardInfo?.sunset!!)
                            }
                        }

                        MyDailyWeatherView(
                            data = state.value?.dailyCardInfo!!,
                            modifier =Modifier)


                        MyWeatherDetailsView(state.value!!.detailsCardInfo!!)
                    }
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

