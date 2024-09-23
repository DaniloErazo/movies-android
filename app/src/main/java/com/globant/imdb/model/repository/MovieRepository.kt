package com.globant.imdb.model.repository

import com.globant.imdb.model.entity.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRepository {

    //TODO Review repository implementation decouple

    @GET("movie/top_rated")
    fun getTopMovies(@Query("api_key") apiKey: String = "749058a6469a1eb756bd200fa7ebb58e"): Call<MovieResponse>

}