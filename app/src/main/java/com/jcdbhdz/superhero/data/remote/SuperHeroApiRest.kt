package com.jcdbhdz.superhero.data.remote

import com.jcdbhdz.superhero.data.model.CharacterDataWrapper
import retrofit2.http.GET
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
}