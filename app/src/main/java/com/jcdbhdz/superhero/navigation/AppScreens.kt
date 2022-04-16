package com.jcdbhdz.superhero.navigation

sealed class AppScreens(val route: String){
    object CharacterListScreen: AppScreens("main")
    object CharacterDetails: AppScreens("details")
    object MoreInformation: AppScreens("information")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
