package com.example.myweatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R

// Set of Material typography styles to start with
val Bebasnue= FontFamily(
    Font(R.font.beabasnue, weight = FontWeight.Light, FontStyle.Normal)
)
val BebasLight= FontFamily(
    Font(R.font.bebas_light, weight = FontWeight.Thin,FontStyle.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Bebasnue,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 22.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = BebasLight,
        fontWeight = FontWeight.Light,
        fontSize = 40.sp,
        lineHeight = 5.sp,
        letterSpacing = 0.5.sp
    )

)