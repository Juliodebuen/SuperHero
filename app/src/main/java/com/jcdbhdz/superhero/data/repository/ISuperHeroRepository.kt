package com.jcdbhdz.superhero.data.repository

import com.jcdbhdz.superhero.data.model.CharacterDataWrapper
import javax.inject.Named

interface ISuperHeroRepository {
    suspend fun getCharacters(limit: Int, offset: Int) : CharacterDataWrapper
}