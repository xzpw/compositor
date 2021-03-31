package com.dm.compositor.di

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import org.koin.dsl.module

val networkModule = module {
    single { provideClient() }
}

private fun provideClient() = HttpClient(OkHttp){
    install(JsonFeature) {
        serializer = GsonSerializer()
    }
}

