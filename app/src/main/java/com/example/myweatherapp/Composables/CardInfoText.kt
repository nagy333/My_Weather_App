package com.example.myweatherapp.Composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun CardInfoText(text:String,modifier: Modifier=Modifier,fontSize:TextUnit=30.sp){
    MyWeatherAppTheme {  Text(text=text,
        modifier=modifier,
        style = MaterialTheme.typography.bodyLarge,
        fontSize = fontSize,
        color = MaterialTheme.colorScheme.background
    )}

}
@Preview(showSystemUi = true)
@Composable
fun p2(){
    MyWeatherAppTheme { CardInfoText("Today") }

}