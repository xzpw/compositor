package com.dm.compositor.core.common

sealed class ViewState {

    object Loading : ViewState()

    class Success<out T: Any>(data: T) : ViewState()

    class Error(msg: String) : ViewState()
}