package com.jcdbhdz.superhero.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jcdbhdz.superhero.R
import com.jcdbhdz.superhero.data.model.Comic
import com.jcdbhdz.superhero.data.model.Event
import com.jcdbhdz.superhero.data.model.Series
import com.jcdbhdz.superhero.data.model.Story
import com.jcdbhdz.superhero.screens.common.ImageLoading
import com.jcdbhdz.superhero.screens.common.NoData
import com.jcdbhdz.superhero.ui.theme.SuperHeroTheme
import com.jcdbhdz.superhero.viewmodel.SuperHeroViewModel


@Composable
fun MoreInformation(
    navController: NavHostController,
    idCharacter: String?,
    title: String?,
    viewModel: SuperHeroViewModel = hiltViewModel()
) {
    SuperHeroTheme {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = colorResource(id = R.color.marvel_red),
                        title = {
                            Text(
                                text = stringResource(title!!.toInt()),
                                color = Color.White,
                                style = MaterialTheme.typography.h1
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
                                Icon(
                                    tint = colorResource(id = R.color.white),
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )
                }
            ){
                requireNotNull(idCharacter)
                when(title!!.toInt()){
                    R.string.comics ->{
                        viewModel.getComicsByCharacterId(idCharacter)
                        val comicList by viewModel.comicList.collectAsState(arrayListOf())
                        if(comicList.isEmpty()){
                            NoData()
                        }else{
                            DisplayComicList(comicList)
                        }
                    }
                    R.string.events ->{
                        viewModel.getEventsByCharacterId(idCharacter)
                        val eventList by viewModel.eventList.collectAsState(arrayListOf())
                        if(eventList.isEmpty()){
                            NoData()
                        }else{
                            DisplayEventList(eventList)
                        }
                    }
                    R.string.stories ->{
                        viewModel.getStoriesByCharacterId(idCharacter)
                        val storiesList by viewModel.storiesList.collectAsState(arrayListOf())
                        if(storiesList.isEmpty()){
                            NoData()
                        }else{
                            DisplayStoriesList(storiesList)
                        }
                    }
                    R.string.series ->{
                        viewModel.getSeriesByCharacterId(idCharacter)
                        val seriesList by viewModel.seriesList.collectAsState(arrayListOf())
                        if(seriesList.isEmpty()){
                            NoData()
                        }else{
                            DisplaySeriesList(seriesList)
                        }
                    }
                }

                val errorMessage by viewModel.errorMessage.collectAsState("")
                if (errorMessage.isNotEmpty()) {
                    Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayStoriesList(storyList: List<Story>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(count = storyList.size) { index ->
            ItemGrid(imagePath = "${storyList[index].thumbnail?.path}.${storyList[index].thumbnail?.extension}", description = storyList[index].description)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplaySeriesList(seriesList: List<Series>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(count = seriesList.size) { index ->
            ItemGrid(imagePath = "${seriesList[index].thumbnail?.path}.${seriesList[index].thumbnail?.extension}", description = seriesList[index].description)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayComicList(comicList: List<Comic>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(count = comicList.size) { index ->
            ItemGrid(imagePath = "${comicList[index].thumbnail?.path}.${comicList[index].thumbnail?.extension}", description = comicList[index].description)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayEventList(eventList: List<Event>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(count = eventList.size) { index ->
            ItemGrid(imagePath = "${eventList[index].thumbnail?.path}.${eventList[index].thumbnail?.extension}", description = eventList[index].description)
        }
    }
}


@Composable
fun ItemGrid(imagePath: String, description: String?){
    Column(modifier = Modifier
        .padding(8.dp)) {
        Box(modifier = Modifier
            .height(250.dp)
            .clip(
                shape = CutCornerShape(
                    topStartPercent = 0,
                    bottomStartPercent = 0,
                    topEndPercent = 0,
                    bottomEndPercent = 10
                )
            )
            .background(color = Color.Black)
        ) {
            Column{
                ImageLoading(imageModel = imagePath, height = 180)
                Box(modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.marvel_red))
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(top = 4.dp),
                    text = if(description.isNullOrBlank()) stringResource(id = R.string.no_information_available)
                    else description,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    color = Color.White,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}