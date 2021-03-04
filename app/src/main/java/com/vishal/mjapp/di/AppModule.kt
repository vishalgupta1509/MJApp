package com.vishal.mjapp.di

import com.vishal.mjapp.repositories.MainRepository
import com.vishal.mjapp.screens.MainViewModel
import com.vishal.mjapp.utils.AppExecutors
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val module = module {
        single { AppExecutors() }
    }

    val viewModels = module {
        factory { MainRepository() }
        viewModel { MainViewModel(get()) }
    }
}