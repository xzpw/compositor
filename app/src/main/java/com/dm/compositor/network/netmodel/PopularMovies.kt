package com.dm.compositor.network.netmodel

import kotlinx.serialization.Serializable

@Serializable
data class PopularMovies(
    val page: Int,
    val results: List<MovieNetModel>,
    val total_pages: Int,
    val total_results: Int
)