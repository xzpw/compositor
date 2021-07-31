package com.dm.compositor.core.contracts

import com.dm.compositor.core.models.Movie
import com.dm.compositor.core.models.Response

interface PopularMovieDataSource {

    suspend fun getPopularMovies(): Response<List<Movie>>
}