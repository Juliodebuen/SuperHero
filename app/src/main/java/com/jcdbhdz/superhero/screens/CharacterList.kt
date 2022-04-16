package com.jcdbhdz.superhero.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jcdbhdz.superhero.R
import com.jcdbhdz.superhero.data.model.Character
import com.jcdbhdz.superhero.navigation.AppScreens
import com.jcdbhdz.superhero.screens.common.ImageLoading
import com.jcdbhdz.superhero.ui.theme.SuperHeroTheme
import com.jcdbhdz.superhero.viewmodel.SuperHeroViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.valentinilk.shimmer.shimmer

@Composable
fun CharacterListScreen(
    viewModel: SuperHeroViewModel = hiltViewModel(),
    navController: NavHostController
) {
    SuperHeroTheme {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = colorResource(id = R.color.marvel_red),
                        title = {
                            Text(
                                text = stringResource(id = R.string.super_heroes),
                                color = Color.White,
                                style = MaterialTheme.typography.h1
                            )
                        }
                    )
                }
            ) {
                val isLoading by viewModel.isLoading.collectAsState(false)
                val errorMessage by viewModel.errorMessage.collectAsState("")
                val characterList by viewModel.characterList.collectAsState(arrayListOf())
                if(errorMessage.isEmpty()){
                    Column(Modifier.fillMaxSize()) {
                        SuperHeroList(
                            isLoading = isLoading,
                            characterList = characterList,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }else{
                    Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
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
        Column {
            Box(modifier = Modifier
                .shimmer()) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(255.dp)
                            .background(Color.LightGray),
                    )
                }
            }
        }
    }
}

@Composable
fun LoadedCard(character: Character, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    AppScreens.CharacterDetails.withArgs(
                        character.id.toString(),
                        character.name.toString()
                    )
                )
            },
    ) {
        Column {
            ImageLoading("${character.thumbnail?.path}.${character.thumbnail?.extension}")
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
fun SuperHeroList(
    isLoading: Boolean,
    characterList: List<Character>,
    viewModel: SuperHeroViewModel,
    navController: NavHostController
) {
    val listState = rememberForeverLazyListState(key = "Overview")
    LazyColumn(state = listState) {
        if (isLoading && characterList.isEmpty()) {
            repeat(10) {
                item {
                    LoadingCard()
                }
            }
        } else {
            items(count = characterList.size) { index ->
                LoadedCard(characterList[index], navController = navController)
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
}

/**
 * Static field, contains all scroll values
 */
private val SaveMap = mutableMapOf<String, KeyParams>()

private data class KeyParams(
    val params: String = "",
    val index: Int,
    val scrollOffset: Int
)

/**
 * Save scroll state on all time.
 * @param key value for comparing screen
 * @param params arguments for find different between equals screen
 * @param initialFirstVisibleItemIndex see [LazyListState.firstVisibleItemIndex]
 * @param initialFirstVisibleItemScrollOffset see [LazyListState.firstVisibleItemScrollOffset]
 */
@Composable
fun rememberForeverLazyListState(
    key: String,
    params: String = "",
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0
): LazyListState {
    val scrollState = rememberSaveable(saver = LazyListState.Saver) {
        var savedValue = SaveMap[key]
        if (savedValue?.params != params) savedValue = null
        val savedIndex = savedValue?.index ?: initialFirstVisibleItemIndex
        val savedOffset = savedValue?.scrollOffset ?: initialFirstVisibleItemScrollOffset
        LazyListState(
            savedIndex,
            savedOffset
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            val lastIndex = scrollState.firstVisibleItemIndex
            val lastOffset = scrollState.firstVisibleItemScrollOffset
            SaveMap[key] = KeyParams(params, lastIndex, lastOffset)
        }
    }
    return scrollState
}