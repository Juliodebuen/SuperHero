package com.jcdbhdz.superhero.data.repository

import com.jcdbhdz.superhero.data.model.*
import com.jcdbhdz.superhero.data.remote.SuperHeroApiRest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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


    override suspend fun getCharacters(limit: Int, offset: Int): Flow<CharacterDataWrapper> = flow{
        emit(apiRest.getCharacters(
            limit = limit,
            offset = offset,
            apiKey = apiKey,
            hash = hash,
            ts = timestamp
        ))
    }.flowOn(Dispatchers.IO)

    override suspend fun getCharacterById(id: String): Flow<CharacterDataWrapper> = flow{
        emit(apiRest.getCharacterById(
            characterId = id,
            apiKey = apiKey,
            hash = hash,
            ts = timestamp
        ))
    }.flowOn(Dispatchers.IO)

    override suspend fun getComicsByCharacterId(id: String): Flow<ComicDataWrapper> = flow{
        emit(apiRest.getComicsByCharacterId(
            characterId = id,
            apiKey = apiKey,
            hash = hash,
            ts = timestamp
        ))
    }.flowOn(Dispatchers.IO)

    override suspend fun getEventsByCharacterId(id: String): Flow<EventDataWrapper> = flow{
        emit(apiRest.getEventsByCharacterId(
            characterId = id,
            apiKey = apiKey,
            hash = hash,
            ts = timestamp
        ))
    }.flowOn(Dispatchers.IO)

    override suspend fun getSeriesByCharacterId(id: String): Flow<SeriesDataWrapper> = flow{
        emit(apiRest.getSeriesByCharacterId(
            characterId = id,
            apiKey = apiKey,
            hash = hash,
            ts = timestamp
        ))
    }.flowOn(Dispatchers.IO)

    override suspend fun getStoriesByCharacterId(id: String): Flow<StoryDataWrapper> = flow{
        emit(apiRest.getStoriesByCharacterId(
            characterId = id,
            apiKey = apiKey,
            hash = hash,
            ts = timestamp
        ))
    }.flowOn(Dispatchers.IO)


}