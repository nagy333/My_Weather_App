package com.example.myweatherapp.Composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun MyLoadingView(modifier: Modifier=Modifier){
    MyWeatherAppTheme {
        Box(
            modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier.size(100.dp),
                strokeWidth = 7.dp,
            )
        }

    }
}
@Preview(showSystemUi = true)
@Composable
fun pL(){
    MyWeatherAppTheme {
        MyLoadingView(Modifier)
    }
}