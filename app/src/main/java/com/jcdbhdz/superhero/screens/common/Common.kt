package com.jcdbhdz.superhero.screens.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jcdbhdz.superhero.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ImageLoading(imageModel: String = "", height: Int = 230) {
    GlideImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp),
        imageModel = imageModel,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(id = R.color.marvel_red)
                )
            }
        },
        failure = {
            NoData(stringResource = R.string.no_image_available, color = Color.White)
        }
        /*shimmerParams = ShimmerParams(
            baseColor = MaterialTheme.colors.background,
            highlightColor = Color.Gray,
            durationMillis = 1000,
            dropOff = 0.65f,
            tilt = 20f
        )*/
    )
}

@Preview(showBackground = true)
@Composable
fun NoData(stringResource: Int = R.string.no_information_available, color: Color = Color.Black){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = stringResource), textAlign = TextAlign.Center, color = color
        )
    }
}