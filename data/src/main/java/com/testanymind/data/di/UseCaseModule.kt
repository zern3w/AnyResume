package com.testanymind.data.di

import com.testanymind.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    single { GetPersonalInfoUseCase(get()) }
    single { SavePersonalInfoUseCase(get()) }
    single { DeleteAllPersonalInfoUseCase(get()) }

    single { GetEducationUseCase(get()) }
    single { GetAllEducationUseCase(get()) }
    single { SaveEducationUseCase(get()) }
    single { SaveAllEducationUseCase(get()) }
    single { UpdateEducationUseCase(get()) }
    single { DeleteEducationUseCase(get()) }
    single { DeleteAllEducationUseCase(get()) }

    single { GetSkillsUseCase(get()) }
    single { SaveSkillsUseCase(get()) }
    single { DeleteAllSkillsUseCase(get()) }

    single { GetWorkingExperienceUseCase(get()) }
    single { SaveWorkingExperienceUseCase(get()) }
    single { DeleteAllWorkingExperienceUseCase(get()) }

    single { GetProjectUseCase(get()) }
    single { SaveProjectUseCase(get()) }
    single { DeleteAllProjectUseCase(get()) }
}