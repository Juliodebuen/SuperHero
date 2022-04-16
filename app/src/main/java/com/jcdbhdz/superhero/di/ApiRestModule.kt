package com.jcdbhdz.superhero.di

import com.jcdbhdz.superhero.data.remote.SuperHeroApiRest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiRestModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun providesBaseUrl() = "https://gateway.marvel.com:443"

    @Singleton
    @Provides
    @Named("ApiKey")
    fun providesApiKey() = "42d757b64218c343de982ecc9c9c3b79"

    @Singleton
    @Provides
    @Named("PrivateKey")
    fun providesPrivateKey() = "97994d905bbbfecc9296d1a2875f98f8116b8b07"

    @Singleton
    @Provides
    @Named("Timestamp")
    fun providesTimestamp() = System.currentTimeMillis().toString()

    @Singleton
    @Provides
    @Named("Hash")
    fun providesHash(
        @Named("Timestamp")ts: String,
        @Named("ApiKey") apiKey: String,
        @Named("PrivateKey") privateKey: String
    ): String {
        val hash = "$ts$privateKey$apiKey"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(hash.toByteArray())).toString(16).padStart(32, '0')
    }

    @Singleton
    @Provides
    fun providesRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun providesSuperHeroApiRest(retrofit: Retrofit): SuperHeroApiRest = retrofit.create(
        SuperHeroApiRest::class.java)
}