package com.nguyen.paypal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "Truong-MainViewModel"
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
            _favorites.value = (_favorites.value ?: emptySet()) + movie
            Log.d(TAG, "adding new favorite: $movie")
        }
    }
}