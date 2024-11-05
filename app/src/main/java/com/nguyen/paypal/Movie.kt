package com.nguyen.paypal

data class Movies(val results: List<Movie>)

const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/w342/"

data class Movie(
    val id: Int,
    val backdrop_path: String,
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Float
) {
    fun poster() = "$IMAGE_PREFIX$poster_path"
    fun backdrop() = "$IMAGE_PREFIX$backdrop_path"
    override fun toString() = "$id:::$title:::$overview"
}
