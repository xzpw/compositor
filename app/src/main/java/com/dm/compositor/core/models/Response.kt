package com.dm.compositor.core.models

sealed class Response<out T : Any> {

    class Success<out T : Any>(val data: T): Response<T>()
    class Error(val exception: Exception): Response <Nothing> ()
}