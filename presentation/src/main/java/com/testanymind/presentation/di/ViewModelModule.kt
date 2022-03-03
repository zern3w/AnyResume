package com.testanymind.presentation.di

import com.testanymind.presentation.feature.education.EducationViewModel
import com.testanymind.presentation.feature.home.MainViewModel
import com.testanymind.presentation.feature.personal.PersonalInfoViewModel
import com.testanymind.presentation.feature.project.ProjectViewModel
import com.testanymind.presentation.feature.skill.SkillViewModel
import com.testanymind.presentation.feature.workexperience.WorkExperienceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {

    viewModel { MainViewModel(get(), get(), get(), get(), get()) }
    viewModel { SkillViewModel(get(), get(), get()) }
    viewModel { EducationViewModel(get(), get(), get()) }
    viewModel { PersonalInfoViewModel(get(), get(), get()) }
    viewModel { WorkExperienceViewModel(get(), get(), get()) }
    viewModel { ProjectViewModel(get(), get(), get()) }

}