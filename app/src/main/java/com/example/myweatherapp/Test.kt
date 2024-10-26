package com.example.myweatherapp

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myweatherapp.ViewModels.MainScreenViewModel
import com.example.myweatherapp.ui.theme.Bebasnue
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import java.time.LocalDate

@Composable
fun Test(){
    Text(text = "Monday",
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
}
@Composable
fun Test2(){
    val isDarkModeEnabled= remember { mutableStateOf(false) }
    MyWeatherAppTheme(darkTheme = isDarkModeEnabled.value) {
        Box(modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)){
            Button(onClick = {isDarkModeEnabled.value=!isDarkModeEnabled.value}) {
                Text("Change the theme")
            }
        }
    }
}

@Composable
fun Test3(){
    val viewModel:MainScreenViewModel= hiltViewModel()
    val textState by viewModel.textState.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {}) {
            Text("Get Data")
        }
        SelectionContainer {
            Text(textState, fontSize = 25.sp)
        }

    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Test4(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        val cal=Calendar.getInstance()
        val month=cal.get(Calendar.MONTH)
        val date=LocalDate.now().dayOfWeek.name
        Text(text=date)

    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun P(){
   Test4()
}