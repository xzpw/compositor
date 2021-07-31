package com.dm.compositor.domain

import com.dm.compositor.core.contracts.PopularMovieDataSource
import com.dm.compositor.core.models.Movie
import com.dm.compositor.core.models.Response

class DashboardUseCase(private val dataSource: PopularMovieDataSource): IDashboardUseCase {

    override suspend fun getMoviesList(): Response<List<Movie>> {
        return dataSource.getPopularMovies()
    }

    override fun addToFavorite() {
        TODO("Not yet implemented")
    }
}

