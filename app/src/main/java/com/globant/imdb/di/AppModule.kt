package com.globant.imdb.di

import android.content.Context
import androidx.room.Room
import com.globant.imdb.database.LocalDatabase
import com.globant.imdb.database.dao.MovieDao
import com.globant.imdb.database.dao.UserDao
import com.globant.imdb.model.services.MovieService
import com.globant.imdb.model.services.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private const val DATABASE_NAME = "local_database"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase{
        return Room.databaseBuilder(context, LocalDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: LocalDatabase): UserDao{
        return db.getUserDao()
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: LocalDatabase): MovieDao {
        return db.getMovieDao()
    }
}