package com.jcdbhdz.superhero.di

import com.jcdbhdz.superhero.data.repository.ISuperHeroRepository
import com.jcdbhdz.superhero.data.repository.SuperHeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun superHeroRepository(repository: SuperHeroRepository): ISuperHeroRepository
}