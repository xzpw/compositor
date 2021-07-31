package com.dm.compositor

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.dm.compositor.network.Constants
import com.dm.compositor.network.netmodel.PopularMovies
import com.dm.compositor.ui.theme.CompositorTheme
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.response.*
import io.ktor.http.*
import io.ktor.http.cio.*
import kotlinx.coroutines.CoroutineScope
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
                    Greeting("Android", applicationContext)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, context: Context) {
    LazyColumn() {
        for (i in 0..30) {
            item {
                Card(text = i.toString(), context)
            }
        }
    }
}

@Composable
fun Card(text: String, context: Context) {
    Column() {
        val image = CoilImage(url = "https://marin.ru/ai/1000/geo.383.thumb.big/saint-tropez-main1(pics.1).jpg",
            context = context).value
        image?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "",
                Modifier.fillMaxWidth()
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "It is row #")
            Text(text = text)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompositorTheme {
        Greeting("Android", LocalContext.current)
    }
}

@Composable
fun CoilImage(url: String, context: Context): MutableState<Bitmap?> {
    var bitmapState: MutableState<Bitmap?>  = remember {
        mutableStateOf(null)
    }

    val imageRequest = ImageRequest.Builder(context = context)
        .data(url)
        .target {
            bitmapState.value = it.toBitmap()
        }.build()
    val imageLoader = ImageLoader.Builder(context).crossfade(true).build()
    imageLoader.enqueue(imageRequest)

    return bitmapState
}