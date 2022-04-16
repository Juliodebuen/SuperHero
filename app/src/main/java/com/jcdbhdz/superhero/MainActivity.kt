package com.jcdbhdz.superhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jcdbhdz.superhero.data.model.Character
import com.jcdbhdz.superhero.navigation.AppNavigation
import com.jcdbhdz.superhero.ui.theme.SuperHeroTheme
import com.jcdbhdz.superhero.viewmodel.SuperHeroViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.valentinilk.shimmer.shimmer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var viewModel: SuperHeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SuperHeroViewModel::class.java)
        setContent {
            /*val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main"){
                composable("main"){
                    SuperHeroTheme {
                        Surface(color = MaterialTheme.colors.background) {
                            Screen()
                        }
                    }
                }
                composable("detail"){
                    SuperHeroTheme {
                        Surface(color = MaterialTheme.colors.background) {
                            Greeting(name = "hola bb")
                        }
                    }
                }
            }*/
            AppNavigation()
        }
    }
/*
    @Composable
    fun Screen() {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = colorResource(id = R.color.marvel_red),
                    title = {
                        Text(
                            text = stringResource(id = R.string.super_heroes),
                            color = Color.White,
                            style =  MaterialTheme.typography.h1
                        )
                    }
                )
            }
        ) {
            val isLoading by viewModel.isLoading.collectAsState(false)
            val characterList by viewModel.characterList.collectAsState(arrayListOf())
            Column(Modifier.fillMaxSize()) {
                SuperHeroList(isLoading = isLoading, characterList = characterList)
            }

        }
    }


    @Composable
    fun LoadingCard() {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 1.dp,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth()
                .testTag("loadingCard")
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                ImageLoading()
                Spacer()
                Column {
                    Spacer()
                    Box(modifier = Modifier.shimmer()) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(15.dp)
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                            )
                            Spacer()
                            Box(
                                modifier = Modifier
                                    .height(15.dp)
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ImageLoading() {
        Box(modifier = Modifier.shimmer()) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Gray)
            ) {

            }
        }
    }

    @Composable
    fun LoadedCard(character: Character) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 1.dp,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth(),
        ) {
            Column {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .offset(0.dp, 0.dp),
                    imageModel = "${character.thumbnail?.path}.${character.thumbnail?.extension}",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.LightGray,
                        durationMillis = 350,
                        dropOff = 0.65f,
                        tilt = 20f
                    ),
                    failure = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.error_image_msg),
                            textAlign = TextAlign.Center
                        )
                    }
                )
                Column(
                    modifier = Modifier
                        .background(colorResource(id = R.color.marvel_red))
                    ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = "${character.name?.uppercase()}", style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )
                }
            }
        }
    }

    @Composable
    fun Spacer(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))

    @Composable
    fun SuperHeroList(isLoading: Boolean, characterList: List<Character>) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            if (isLoading && characterList.isEmpty()) {
                repeat(10) {
                    item {
                        LoadingCard()
                    }
                }
            } else {
                items(count = characterList.size) { index ->
                    LoadedCard(characterList[index])
                }
                item{
                    if(isLoading)
                        LoadingCard()
                }
            }
        }

        listState.OnBottomReached(buffer = 10) {
            viewModel.getCharacterList()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun SuperHeroGrid(isLoading: Boolean, characterList: List<Character>) {
        val listState = rememberLazyListState()
        LazyVerticalGrid(
            state = listState,
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp)
        ) {
            if (isLoading && characterList.isEmpty()) {
                repeat(10) {
                    item {
                        LoadingCard()
                    }
                }
            } else {
                items(count = characterList.size) { index ->
                    LoadedCard(characterList[index])
                }
                item{
                    if(isLoading)
                        LoadingCard()
                }
            }
        }

        listState.OnBottomReached(buffer = 10) {
            viewModel.getCharacterList()
        }
    }

    @Composable
    fun LazyListState.OnBottomReached(
        // tells how many items before we reach the bottom of the list
        // to call onLoadMore function
        buffer : Int = 0,
        onLoadMore : () -> Unit
    ) {
        // Buffer must be positive.
        // Or our list will never reach the bottom.
        require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

        val shouldLoadMore = remember {
            derivedStateOf {
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                    ?:
                    return@derivedStateOf true

                // subtract buffer from the total items
                lastVisibleItem.index >=  layoutInfo.totalItemsCount - 1 - buffer
            }
        }

        LaunchedEffect(shouldLoadMore){
            snapshotFlow { shouldLoadMore.value }
                .collect { if (it) onLoadMore() }
        }
    }*/
}