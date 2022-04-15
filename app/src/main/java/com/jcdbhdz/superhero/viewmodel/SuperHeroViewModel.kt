package com.jcdbhdz.superhero.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcdbhdz.superhero.data.repository.SuperHeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val superHeroRepository: SuperHeroRepository
): ViewModel() {

    fun getCharacter(){
        viewModelScope.launch(Dispatchers.IO) {
            val characters = superHeroRepository.getCharacters(10,10)
            Log.d("Personajes", characters.toString())
        }
    }
}