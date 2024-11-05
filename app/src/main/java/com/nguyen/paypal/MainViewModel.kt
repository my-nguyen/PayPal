package com.nguyen.paypal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _favorites = MutableLiveData<Set<Movie>>()
    val favorites: LiveData<Set<Movie>> = _favorites

    init {
        viewModelScope.launch {
            _movies.postValue(Repository.getNowPlaying())
        }
    }

    fun addFavorite(movie: Movie) {
        viewModelScope.launch {
            _favorites.postValue(_favorites.value?.toMutableSet().also { it?.add(movie) })
        }
    }
}