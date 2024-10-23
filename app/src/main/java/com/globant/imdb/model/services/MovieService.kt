package com.globant.imdb.model.services

import com.globant.imdb.model.entity.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    // Review repository implementation several datasources
    //Login using Room database  - dependency injection  -
    // Review coroutine call in retrofit
    //https://unluckluck.medium.com/using-two-or-more-base-urls-with-kotlin-and-retrofit-a70f5d9a5798

    @GET("movie/top_rated")
    fun getTopMovies(@Query("api_key") apiKey: String = "749058a6469a1eb756bd200fa7ebb58e"): Call<MovieResponse>

}