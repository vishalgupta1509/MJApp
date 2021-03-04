package com.vishal.mjapp.baseFiles

import android.app.Application
import com.vishal.mjapp.di.AppModule
import com.vishal.mjapp.network.networkModule
import org.koin.android.java.KoinAndroidApplication
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val koin =KoinAndroidApplication.create(this)
                .logger(AndroidLogger(Level.ERROR))
                .modules(
                        AppModule.module,
                        AppModule.viewModels,
                        networkModule
                )

        startKoin(
                GlobalContext(), koin
        )
    }
}