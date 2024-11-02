package com.example.myweatherapp.Composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit

@Composable
fun InfoTextBold(
            text:String
             ,modifier: Modifier,
             style:TextStyle=MaterialTheme.typography.bodyLarge,
           fontSize:TextUnit= MaterialTheme.typography.bodyLarge.fontSize){
    Text(text=text,
        style = style,
        color =MaterialTheme.colorScheme.primary,
        modifier = modifier,
        fontSize =fontSize )
}

@Composable
fun InfoTextLight(
    text:String
    , modifier: Modifier,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    fontSize: TextUnit = MaterialTheme.typography.labelSmall.fontSize){
    Text(text=text,
        style = style,
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier,
        fontSize =fontSize )
}

@Preview(showSystemUi = true)
@Composable
fun PP(){
    InfoTextBold("Matay",Modifier,MaterialTheme.typography.labelSmall)
}