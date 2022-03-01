package com.testanymind.presentation.di

import com.testanymind.presentation.view.activity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    viewModel { MainViewModel() }

}