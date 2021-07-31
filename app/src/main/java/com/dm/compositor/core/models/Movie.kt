package com.dm.compositor.core.models

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)