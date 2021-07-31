package com.dm.compositor.domain

import com.dm.compositor.core.models.Movie
import com.dm.compositor.core.models.Response

interface IDashboardUseCase {

    suspend fun getMoviesList(): Response<List<Movie>>

    fun addToFavorite()

}