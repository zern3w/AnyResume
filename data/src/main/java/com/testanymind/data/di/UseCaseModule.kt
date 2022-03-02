package com.testanymind.data.di

import com.testanymind.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { GetSkillsUseCase(get()) }
    single { SaveSkillsUseCase(get()) }
    single { DeleteAllSkillsUseCase(get()) }

    single { GetPersonalInfoUseCase(get()) }
    single { SavePersonalInfoUseCase(get()) }
    single { DeleteAllPersonalInfoUseCase(get()) }

    single { GetEducationUseCase(get()) }
    single { SaveEducationUseCase(get()) }
    single { DeleteAllEducationUseCase(get()) }

    single { GetWorkingExperienceUseCase(get()) }
    single { SaveWorkingExperienceUseCase(get()) }
    single { DeleteAllWorkingExperienceUseCase(get()) }

    single { GetProjectUseCase(get()) }
    single { SaveProjectUseCase(get()) }
    single { DeleteAllProjectUseCase(get()) }
}