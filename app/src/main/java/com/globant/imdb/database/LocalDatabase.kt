package com.globant.imdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globant.imdb.database.dao.MovieDao
import com.globant.imdb.database.dao.UserDao
import com.globant.imdb.database.entities.MovieDB
import com.globant.imdb.database.entities.User

@Database(entities = [User::class, MovieDB::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun getUserDao():UserDao

    abstract fun getMovieDao():MovieDao
}