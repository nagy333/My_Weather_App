package com.example.myweatherapp.Screens

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.example.myweatherapp.Composables.InfoTextBold
import com.example.myweatherapp.Composables.MyBottomSheet
import com.example.myweatherapp.ViewModels.LocationScreenViewModel
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme

@Composable
fun LocationScreen(){

    val viewModel:LocationScreenViewModel = hiltViewModel()

    val pState=viewModel.state.collectAsState()
    MyWeatherAppTheme {
        LocationScreenContent(
            state = pState,
           viewModel::updateValue,
            viewModel::onSearchClicked)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreenContent(
    state: State<String>,
    onValueChange:(String)->Unit,
    onSearchClicked:()->Unit
) {
    MyWeatherAppTheme {
        val sheetState= rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            topBar = {
                TopAppBar(title = {
                    InfoTextBold(text = "Locations",Modifier)
                },
                    colors = TopAppBarDefaults.
                    topAppBarColors(containerColor = MaterialTheme.colorScheme.background))
            }, floatingActionButton = {
                FloatingActionButton(onClick = {},
                    modifier = Modifier.clip(CircleShape)
                , containerColor = MaterialTheme.colorScheme.primary) {
                    IconButton(onClick = {showBottomSheet=true}
                    ) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            )
                    }
                }
            }
        ) {padding->
           Box(modifier = Modifier
               .fillMaxSize()
               .padding(padding)){
               if (showBottomSheet){
                   ModalBottomSheet(onDismissRequest = { showBottomSheet = false },
                       sheetState=sheetState) {
                       MyBottomSheet(state.value,
                           onValueChange = onValueChange,
                           onSearchClicked)
                   }
               }
           }
        }

    }


}
@Preview(showSystemUi = true)
@Composable
fun ppp(){
    MyWeatherAppTheme {   LocationScreen() }

}