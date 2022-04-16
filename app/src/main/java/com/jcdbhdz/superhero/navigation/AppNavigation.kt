package com.jcdbhdz.superhero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jcdbhdz.superhero.screens.DetailPage
import com.jcdbhdz.superhero.screens.CharacterListScreen
import com.jcdbhdz.superhero.screens.MoreInformation

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.CharacterListScreen.route){
        composable(route = AppScreens.CharacterListScreen.route){
            CharacterListScreen(navController = navController)
        }
        composable(
            route = AppScreens.CharacterDetails.route + "/{idCharacter}/{name}",
            arguments = listOf(
                navArgument("idCharacter"){type = NavType.StringType},
                navArgument("name"){type = NavType.StringType}
            )
        ){ backStackEntry->
            val id = backStackEntry.arguments?.getString("idCharacter")
            val name = backStackEntry.arguments?.getString("name")
            DetailPage(navController = navController, idCharacter = id, name = name)
        }
        composable(
            route = AppScreens.MoreInformation.route + "/{idCharacter}/{title}",
            arguments = listOf(
                navArgument("idCharacter"){type = NavType.StringType},
                navArgument("title"){type = NavType.StringType}
            )
        ){ backStackEntry->
            val id = backStackEntry.arguments?.getString("idCharacter")
            val title = backStackEntry.arguments?.getString("title")
            MoreInformation(navController = navController, idCharacter = id, title = title)
        }
    }
}