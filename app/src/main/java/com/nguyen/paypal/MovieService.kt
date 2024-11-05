package com.nguyen.paypal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String): Movies

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Movies
    @GET("movie/{movie_id}/similar")
    fun getSimilar2(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Call<Movies>
}