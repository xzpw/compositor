package com.dm.compositor.di

import com.dm.compositor.domain.DashboardUseCase
import com.dm.compositor.network.datasource.popular.PopularMovieNetworkDataSource
import com.dm.compositor.ui.theme.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { DashboardViewModel(get()) }
    single { DashboardUseCase(get()) }
    single { PopularMovieNetworkDataSource(get()) }
}