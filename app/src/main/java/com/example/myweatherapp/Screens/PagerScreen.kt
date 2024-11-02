package com.example.myweatherapp.Screens

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myweatherapp.UseCases.Indexx
import com.example.myweatherapp.ViewModels.MainScreenViewModel
import com.example.myweatherapp.ViewModels.PagerScreenViewModel
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme


@Composable
fun MyPagerScreen(navController: NavController,index:Int){
    val viewModel:PagerScreenViewModel= hiltViewModel()
    val mainViewModel:MainScreenViewModel = hiltViewModel()
    val state=viewModel.state.collectAsState()
MyWeatherAppTheme {
    MyPagerScreenContent(state = state, updateIndex = mainViewModel::updateIndex,navController)
}
}
@Composable
fun MyPagerScreenContent(
    state:State<Int>,
    updateIndex:(Int)->Unit,
    navController: NavController){
    MyWeatherAppTheme {
        val pagerState = rememberPagerState(pageCount = {state.value})
        HorizontalPager(state = pagerState) {page->
            updateIndex(page)
            MainScreen(navController,page)
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PPager(){
    MyWeatherAppTheme {
        //MyPagerScreen()
    }

}