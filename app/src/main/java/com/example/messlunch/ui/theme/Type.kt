package com.example.messlunch.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messlunch.R

// Set of Material typography styles to start with
val akshar = FontFamily(
    Font(R.font.akshar_regular, FontWeight.Normal),
    Font(R.font.akshar_bold, FontWeight.Bold)
)
val allerta = FontFamily(
    Font(R.font.allerta_regular)
)
val alata =FontFamily(
    Font(R.font.alata_regular)
)
val Typography = Typography(

    displayLarge = TextStyle(
        fontFamily = alata,
        fontSize = 64.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = akshar,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = akshar,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontFamily = allerta
    )
)