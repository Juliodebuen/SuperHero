package com.jcdbhdz.superhero.data.repository

import com.jcdbhdz.superhero.data.model.CharacterDataWrapper
import com.jcdbhdz.superhero.data.remote.SuperHeroApiRest
import java.sql.Timestamp
import javax.inject.Inject
import javax.inject.Named

class SuperHeroRepository @Inject constructor(
    private val apiRest: SuperHeroApiRest
):ISuperHeroRepository{
    @Inject
    @Named("ApiKey")
    lateinit var apiKey: String

    @Inject
    @Named("Timestamp")
    lateinit var timestamp: String

    @Inject
    @Named("Hash")
    lateinit var hash: String


    override suspend fun getCharacters(limit: Int, offset: Int): CharacterDataWrapper {
        return apiRest.getCharacters(
            limit = limit,
            offset = offset,
            apiKey = apiKey,
            hash = hash,
            ts = timestamp
            )
    }
}