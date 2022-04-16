package com.jcdbhdz.superhero.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jcdbhdz.superhero.R

val MarvelFont = FontFamily(
    Font(R.font.marvel_regular),
)

val ComicFont = FontFamily(
    Font(R.font.comic_neue_light, FontWeight.W100),
    Font(R.font.comic_neue, FontWeight.W200),
    Font(R.font.comic_neue_italic, FontWeight.W300),
    Font(R.font.comic_neue_bold, FontWeight.W400),
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = ComicFont,
        fontWeight = FontWeight.W200,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = MarvelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    h1 = TextStyle(
        fontFamily = MarvelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    button = TextStyle(
        fontFamily = MarvelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )

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