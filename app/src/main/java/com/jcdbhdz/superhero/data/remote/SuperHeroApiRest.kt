package com.jcdbhdz.superhero.data.remote

import com.jcdbhdz.superhero.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SuperHeroApiRest {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int,
        @Query("apikey")apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") ts:String
    ) : CharacterDataWrapper

    @GET("/v1/public/characters/{charactedId}")
    suspend fun getCharacterById(
        @Path("charactedId") characterId: String,
        @Query("apikey")apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") ts:String
    ) : CharacterDataWrapper

    @GET("/v1/public/characters/{charactedId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("charactedId") characterId: String,
        @Query("apikey")apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") ts:String
    ) : ComicDataWrapper

    @GET("/v1/public/characters/{charactedId}/events")
    suspend fun getEventsByCharacterId(
        @Path("charactedId") characterId: String,
        @Query("apikey")apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") ts:String
    ) : EventDataWrapper

    @GET("/v1/public/characters/{charactedId}/series")
    suspend fun getSeriesByCharacterId(
        @Path("charactedId") characterId: String,
        @Query("apikey")apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") ts:String
    ) : SeriesDataWrapper

    @GET("/v1/public/characters/{charactedId}/stories")
    suspend fun getStoriesByCharacterId(
        @Path("charactedId") characterId: String,
        @Query("apikey")apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") ts:String
    ) : StoryDataWrapper
}