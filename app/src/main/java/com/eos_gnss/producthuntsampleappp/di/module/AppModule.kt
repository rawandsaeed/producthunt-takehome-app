package com.eos_gnss.producthuntsampleappp.di.module

import com.eos_gnss.producthuntsampleappp.data.datasource.remote.RemoteDataSourceImpl
import com.eos_gnss.producthuntsampleappp.data.datasource.remote.RemoteDataSource
import com.eos_gnss.producthuntsampleappp.data.repository.AppRepository
import com.eos_gnss.producthuntsampleappp.ui.detail.PostDetailViewModel
import com.eos_gnss.producthuntsampleappp.ui.home.HomeViewModel
import com.eos_gnss.producthuntsampleappp.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single<RemoteDataSource> {RemoteDataSourceImpl()}
}