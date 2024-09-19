package com.globant.imdb.model.services

import com.globant.imdb.model.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {

    private const val baseURL = "https://api.themoviedb.org/3/"

    private val movieService = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val movieRepository: MovieRepository = movieService.create(MovieRepository::class.java)
}