package com.globant.imdb.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.imdb.model.entity.MovieDTO
import com.globant.imdb.model.services.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityVM: ViewModel() {

    var movies = MutableLiveData<ArrayList<MovieDTO>>()

     fun loadMovies(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitServices.movieRepository.getTopMovies().execute()
            movies.postValue(response.body()?.results)

        }
    }


}