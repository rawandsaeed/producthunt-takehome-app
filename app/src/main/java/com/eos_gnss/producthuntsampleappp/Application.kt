package com.eos_gnss.producthuntsampleappp

import com.eos_gnss.producthuntsampleappp.di.module.applicationModule
import com.eos_gnss.producthuntsampleappp.di.module.repoModule
import com.eos_gnss.producthuntsampleappp.di.module.viewModels
import org.koin.android.ext.android.startKoin

class Application(): android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = applicationContext,
            modules = listOf(
                applicationModule,
                repoModule,
                viewModels
            )
        )
    }
}