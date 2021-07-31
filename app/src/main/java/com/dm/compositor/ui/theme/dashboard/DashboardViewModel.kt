package com.dm.compositor.ui.theme.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dm.compositor.core.common.ViewState
import com.dm.compositor.core.models.Response
import com.dm.compositor.domain.DashboardUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class DashboardViewModel(val dashboardUseCase: DashboardUseCase) : ViewModel() {

    private val _viewStateLiveData: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewStateLiveData: StateFlow<ViewState> = _viewStateLiveData.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _viewStateLiveData.value = when(val data = dashboardUseCase.getMoviesList()) {
                is Response.Error ->  ViewState.Error(data.exception.toString())
                is Response.Success -> ViewState.Success(data.data)
            }
        }
    }
}