package com.testanymind.presentation.di

import com.testanymind.presentation.view.*
import com.testanymind.presentation.view.activity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    viewModel { MainViewModel() }
    viewModel { SkillViewModel(get(), get(), get()) }
    viewModel { EducationViewModel(get(), get(), get()) }
    viewModel { PersonalInfoViewModel(get(), get(), get()) }
    viewModel { WorkExperienceViewModel(get(), get(), get()) }
    viewModel { ProjectViewModel(get(), get(), get()) }

}