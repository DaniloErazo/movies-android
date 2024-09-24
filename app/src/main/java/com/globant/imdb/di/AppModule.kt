package com.globant.imdb.di

import com.globant.imdb.model.repository.MovieRepository
import com.globant.imdb.model.services.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMovieRepo(): MovieRepository{
        return RetrofitServices.movieRepository
    }
}