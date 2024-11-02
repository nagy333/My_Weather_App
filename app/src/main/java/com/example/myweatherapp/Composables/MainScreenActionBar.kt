package com.example.myweatherapp.Composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMainScreenActionBar(
    text:String,
    onLocationsClicked:()->Unit){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                modifier = Modifier.padding(8.dp),
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier.padding(top = 8.dp),
        actions = {
            IconButton(onClick = onLocationsClicked) {
                Icon(imageVector = Icons.Filled.LocationOn,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(50.dp))
            }
        }

    )
}
@Preview(showSystemUi = true)
@Composable
fun PBar(){
    MyMainScreenActionBar("Monday",{})
}