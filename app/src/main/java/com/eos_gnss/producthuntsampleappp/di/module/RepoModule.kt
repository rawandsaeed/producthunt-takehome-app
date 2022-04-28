package com.eos_gnss.producthuntsampleappp.di.module

import com.eos_gnss.producthuntsampleappp.data.repository.AppRepository
import org.koin.dsl.module.module


val repoModule = module {
    single { AppRepository(get()) }
}