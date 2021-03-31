package com.dm.compositor

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.dm.compositor.network.Constants
import com.dm.compositor.network.netmodel.PopularMovies
import com.dm.compositor.ui.theme.CompositorTheme
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.response.*
import io.ktor.http.*
import io.ktor.http.cio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    val client: HttpClient by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositorTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = client.get<PopularMovies>(Constants.BASE_URL + Constants.POPULAR_MOVIES) {
                    parameter("api_key", Constants.API_KEY)
                }
                Log.d("mylog", response.results[0].title)
            } catch (e: Exception) {
                Log.e("mylog", e.toString())
            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    LazyColumn() {
        for (i in 0..20) {
            item {
                Card(text = i.toString())
            }
        }
    }
}

@Composable
fun Card(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
        Text(text = "It is row #")
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompositorTheme {
        Greeting("Android")
    }
}