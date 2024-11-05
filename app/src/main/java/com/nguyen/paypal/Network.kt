package com.nguyen.paypal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "1fca74d1a066b2433a06dea9b96239fe"

object Network {
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    val service: MovieService by lazy { retrofit.create(MovieService::class.java) }

    suspend fun getNowPlaying(): List<Movie> = service.getNowPlaying(API_KEY).results

    suspend fun getSimilar(movieId: Int): List<Movie> = service.getSimilar(movieId, API_KEY).results
}