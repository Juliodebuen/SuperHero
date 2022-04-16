package com.jcdbhdz.superhero.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.jcdbhdz.superhero.R
import com.jcdbhdz.superhero.navigation.AppScreens
import com.jcdbhdz.superhero.ui.theme.Black
import com.jcdbhdz.superhero.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplash(navController: NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(AppScreens.CharacterListScreen.route)
    }
    Splash(alpha = alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float){
    Box(
        modifier = Modifier
            .background(color = if (isSystemInDarkTheme()) Black else White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.alpha(alpha = alpha),
            painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.marvel_unlimited_black else R.drawable.marvel_unlimited),
            contentDescription = "splash")
    }
}