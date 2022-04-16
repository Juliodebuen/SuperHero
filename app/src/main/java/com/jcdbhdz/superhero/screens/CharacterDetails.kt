package com.jcdbhdz.superhero.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jcdbhdz.superhero.R
import com.jcdbhdz.superhero.data.model.Character
import com.jcdbhdz.superhero.data.model.Comic
import com.jcdbhdz.superhero.navigation.AppScreens
import com.jcdbhdz.superhero.screens.common.ImageLoading
import com.jcdbhdz.superhero.screens.common.Spacer
import com.jcdbhdz.superhero.ui.theme.SuperHeroTheme
import com.jcdbhdz.superhero.viewmodel.SuperHeroViewModel

@Composable
fun DetailPage(
    navController: NavHostController,
    idCharacter: String?,
    name: String?,
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
                                text = name!!.uppercase(),
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
            ) {
                requireNotNull(idCharacter)
                viewModel.getCharacterById(idCharacter)

               // val isLoading by viewModel.isLoading.collectAsState(false)
                val errorMessage by viewModel.errorMessage.collectAsState("")
                val characterList by viewModel.selectedCharacter.collectAsState(arrayListOf())

                if (errorMessage.isEmpty()) {
                    DisplayInformation(characterList, navController = navController)
                }else{
                    Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun DisplayInformation(characterList: List<Character>, navController: NavHostController){
    if(characterList.isNotEmpty()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
            ) {
            ImageLoading("${characterList.first().thumbnail?.path}.${characterList.first().thumbnail?.extension}")
            CharacterDescription(description = characterList.first().description)
            Spacer(modifier = Modifier.weight(1f))
            AditionalInformation(characterId = characterList.first().id, navController = navController)
            Spacer()
        }
    }
}

@Composable
fun CharacterDescription(description: String?) {
    Text(
        text = if(description.isNullOrBlank()) stringResource(id = R.string.no_description_available) else description,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(8.dp))
}

//@Preview(showBackground = true)
@Composable
fun AditionalInformation(navController: NavHostController, characterId: Int?){
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.marvel_red),
                    contentColor = Color.White
                ),
                shape = CutCornerShape(
                    topStartPercent = 35,
                    bottomStartPercent = 0,
                    topEndPercent = 0,
                    bottomEndPercent = 35
                ),
                onClick = {
                    navController.navigate(AppScreens.MoreInformation.withArgs(characterId.toString(), R.string.comics.toString()))
                }) {
                Text(
                    text = stringResource(id = R.string.comics),
                    style = MaterialTheme.typography.button,
                )
            }
            Spacer()
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.marvel_red),
                    contentColor = Color.White
                ),
                shape = CutCornerShape(
                    topStartPercent = 35,
                    bottomStartPercent = 0,
                    topEndPercent = 0,
                    bottomEndPercent = 35
                ),
                onClick = {
                    navController.navigate(AppScreens.MoreInformation.withArgs(characterId.toString(), R.string.events.toString()))
                }) {
                Text(
                    text = stringResource(id = R.string.events),
                    style = MaterialTheme.typography.button
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.marvel_red),
                    contentColor = Color.White
                ),
                shape = CutCornerShape(
                    topStartPercent = 35,
                    bottomStartPercent = 0,
                    topEndPercent = 0,
                    bottomEndPercent = 35
                ),
                onClick = {
                    navController.navigate(AppScreens.MoreInformation.withArgs(characterId.toString(), R.string.series.toString()))
                }) {
                Text(
                    text = stringResource(id = R.string.series),
                    style = MaterialTheme.typography.button,
                )
            }
            Spacer()
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.marvel_red),
                    contentColor = Color.White
                ),
                shape = CutCornerShape(
                    topStartPercent = 35,
                    bottomStartPercent = 0,
                    topEndPercent = 0,
                    bottomEndPercent = 35
                ),
                onClick = {
                    navController.navigate(AppScreens.MoreInformation.withArgs(characterId.toString(), R.string.stories.toString()))
                }) {
                Text(
                    text = stringResource(id = R.string.stories),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}