package com.nguyen.paypal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SimilarViewModel : ViewModel() {
    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun getSimilar(movieId: Int) {
        viewModelScope.launch {
            _movies.postValue(Repository.getSimilar(movieId))
        }
    }
}