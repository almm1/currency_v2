package com.example.curency_v2.presentation.theme.ui

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        color = Color.White
    ),
    h1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        color = Color.White
    ),
    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        color = mWhite
    ),
    h4 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        color = Color.White
    ),

/* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)