package com.example.myweatherapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myweatherapp.ViewModels.PagerScreenViewModel
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme


@Composable
fun MyPagerScreen(){
    val viewModel:PagerScreenViewModel= hiltViewModel()
    val state=viewModel.state.collectAsState()
MyWeatherAppTheme {
    MyPagerScreenContent(state = state)
}
}
@Composable
fun MyPagerScreenContent(state:State<Int>){
    MyWeatherAppTheme {
        val pagerState = rememberPagerState(pageCount = {state.value})
        HorizontalPager(state = pagerState) {

        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PPager(){
    MyWeatherAppTheme {
        MyPagerScreen()
    }

}