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
    single { GetAllWorkingExperienceUseCase(get()) }
    single { SaveWorkingExperienceUseCase(get()) }
    single { UpdateWorkingExperienceUseCase(get()) }
    single { DeleteWorkingExperienceUseCase(get()) }
    single { DeleteAllWorkingExperienceUseCase(get()) }

    single { GetProjectUseCase(get()) }
    single { GetAllProjectUseCase(get()) }
    single { SaveProjectUseCase(get()) }
    single { SaveAllProjectUseCase(get()) }
    single { UpdateProjectUseCase(get()) }
    single { DeleteProjectUseCase(get()) }
    single { DeleteAllProjectUseCase(get()) }
}