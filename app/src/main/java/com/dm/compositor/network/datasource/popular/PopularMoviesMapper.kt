package com.dm.compositor.network.datasource.popular

import com.dm.compositor.core.models.Movie
import com.dm.compositor.network.netmodel.PopularMovies

fun PopularMovies.toCoreMovieList(): List<Movie> {
    val movies = mutableListOf<Movie>()

    this.results.forEach { movieNetModel ->
        movieNetModel.apply {
            movies.add(
                Movie(
                    adult = adult,
                    backdropPath = backdrop_path,
                    id = id,
                    originalTitle = original_title,
                    overview = overview,
                    posterPath = poster_path,
                    releaseDate = release_date,
                    title = title,
                    voteAverage = vote_average
                )
            )
        }
    }
    return movies
}