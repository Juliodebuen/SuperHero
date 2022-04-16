package com.jcdbhdz.superhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.jcdbhdz.superhero.navigation.AppNavigation
import com.jcdbhdz.superhero.viewmodel.SuperHeroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var viewModel: SuperHeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SuperHeroViewModel::class.java)
        setContent {
            AppNavigation()
        }
    }
}