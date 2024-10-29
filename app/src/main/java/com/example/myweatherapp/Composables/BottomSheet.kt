package com.example.myweatherapp.Composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.ui.theme.Bebasnue
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme


@Composable
fun MyBottomSheet(
    text:String,
    onValueChange:(String)->Unit,
    onSearchClicked:()->Unit){
    MyWeatherAppTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) ){
            TextField(
                value =text,
                onValueChange = onValueChange,
                trailingIcon = {
                    IconButton(onSearchClicked ) {
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = "", tint = MaterialTheme.colorScheme.background)
                    }},
                textStyle = TextStyle(color = MaterialTheme.colorScheme.background,
                    fontSize = 25.sp, fontFamily = Bebasnue),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    focusedPlaceholderColor = Color.DarkGray,
                    unfocusedPlaceholderColor = Color.DarkGray,
                    focusedTextColor = MaterialTheme.colorScheme.background,
                    unfocusedTextColor = MaterialTheme.colorScheme.background),
                placeholder = {
                    Text(text = "Enter Location", fontSize = 20.sp)
                }
             ,modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)))
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PBottomSheet(){
    MyWeatherAppTheme { MyBottomSheet("text",{},{}) }
}