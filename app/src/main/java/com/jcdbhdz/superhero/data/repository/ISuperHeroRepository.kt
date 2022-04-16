package com.jcdbhdz.superhero.data.repository

import com.jcdbhdz.superhero.data.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Named

interface ISuperHeroRepository {
    suspend fun getCharacters(limit: Int, offset: Int) : Flow<CharacterDataWrapper>
    suspend fun getCharacterById(id: String): Flow<CharacterDataWrapper>
    suspend fun getComicsByCharacterId(id: String): Flow<ComicDataWrapper>
    suspend fun getEventsByCharacterId(id: String): Flow<EventDataWrapper>
    suspend fun getSeriesByCharacterId(id: String): Flow<SeriesDataWrapper>
    suspend fun getStoriesByCharacterId(id: String): Flow<StoryDataWrapper>
}