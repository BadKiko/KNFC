package com.anonixteam.anonixvpn.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.anonixteam.anonixvpn.R

private val quantityFamily = FontFamily(
    Font(R.font.quantify, FontWeight.Normal)
)

private val monserratFamily = FontFamily(
    Font(R.font.monserrat_bold, FontWeight.Bold),
    Font(R.font.monserrat_semibold, FontWeight.SemiBold),
    Font(R.font.monserrat_medium, FontWeight.Medium),
    Font(R.font.monserrat_regular, FontWeight.Normal),
    Font(R.font.monserrat_thin, FontWeight.Thin)

)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    displayMedium = TextStyle(

    ),
    titleLarge = TextStyle(
        fontFamily = quantityFamily,
        fontSize = 24.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)