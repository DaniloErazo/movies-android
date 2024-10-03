package com.globant.imdb.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.globant.imdb.database.entities.MovieDB

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE identifier = :id")
    suspend fun getMovieById(id:String):MovieDB

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieDB>
}