package com.globant.imdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.imdb.model.entity.MovieDTO
import com.globant.imdb.model.repository.MovieRepository
import com.globant.imdb.model.services.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val repository: MovieRepository)
    : ViewModel() {

    var movies = MutableLiveData<ArrayList<MovieDTO>>()

    var dataFetched = MutableLiveData<Boolean>()


     fun loadMovies(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getTopMovies()
            movies.postValue(response.body()?.results)
            dataFetched.postValue(true)

        }
    }


}