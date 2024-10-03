package com.globant.imdb.model.repository

import com.globant.imdb.database.dao.MovieDao
import com.globant.imdb.database.entities.MovieDB
import com.globant.imdb.model.entity.MovieDTO
import com.globant.imdb.model.entity.MovieResponse
import com.globant.imdb.model.services.MovieService
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: MovieService, private val movieDao: MovieDao ) {

    suspend fun getTopMovies(): Response<MovieResponse> {
        return apiService.getTopMovies().execute()
    }

    suspend fun getLocalMovies(): List<MovieDB>{
        return movieDao.getAllMovies()
    }
}