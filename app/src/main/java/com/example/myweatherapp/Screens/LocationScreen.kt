package com.example.myweatherapp.Screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.loader.content.Loader
import androidx.navigation.NavController
import com.example.myweatherapp.ViewModels.LocationScreenViewModel

@Composable
fun LocationScreen(){
    val viewModel:LocationScreenViewModel = hiltViewModel()

    val pState=viewModel.state.collectAsState()

    LocationScreenContent(
        state = pState,
        onValueChange = viewModel::updateValue)
}
@Composable
fun LocationScreenContent(
    state:State<String>,
    onValueChange:(String)->Unit){
    var showText by remember { mutableStateOf(false) }
    TextField(
        value = state.value,
        onValueChange = onValueChange
        ,
        trailingIcon ={
            IconButton(onClick = {
                showText=true
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        },
        modifier = Modifier.padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
    )

}
@Preview(showSystemUi = true)
@Composable
fun ppp(){
    LocationScreen()
}