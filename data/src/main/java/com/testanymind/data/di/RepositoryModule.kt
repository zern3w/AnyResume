package com.testanymind.data.di

import com.testanymind.data.repository.*
import com.testanymind.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {

    factory<SkillRepository> { SkillRepositoryImpl(get()) }
    factory<PersonalInfoRepository> { PersonalInfoRepositoryImpl(get()) }
    factory<EducationRepository> { EducationRepositoryImpl(get()) }
    factory<WorkingExperienceRepository> { WorkingExperienceRepositoryImpl(get()) }
    factory<ProjectRepository> { ProjectRepositoryImpl(get()) }
}