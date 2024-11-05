package com.nguyen.paypal

object Repository {
    suspend fun getNowPlaying(): List<Movie> = Network.getNowPlaying()

    suspend fun getSimilar(movieId: Int): List<Movie> = Network.getSimilar(movieId)
}