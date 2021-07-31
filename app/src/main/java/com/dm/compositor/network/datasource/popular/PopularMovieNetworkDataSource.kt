package com.dm.compositor.network.datasource.popular

import android.util.Log
import com.dm.compositor.core.contracts.PopularMovieDataSource
import com.dm.compositor.core.models.Movie
import com.dm.compositor.core.models.Response
import com.dm.compositor.network.Constants
import com.dm.compositor.network.netmodel.PopularMovies
import io.ktor.client.*
import io.ktor.client.request.*
import java.lang.Exception

class PopularMovieNetworkDataSource(private val client: HttpClient) : PopularMovieDataSource {

    override suspend fun getPopularMovies(): Response<List<Movie>> {
        return try {
            Response.Success(
                client.get<PopularMovies>(Constants.BASE_URL + Constants.POPULAR_MOVIES) {
                    parameter("api_key", Constants.API_KEY)
            }.toCoreMovieList())

        } catch (e: Exception) {
            Log.e("mylog", e.toString())
            Response.Error(e)
        }
    }
}