package com.testanymind.data.di

import com.testanymind.data.repository.SkillRepositoryImpl
import com.testanymind.domain.repository.SkillRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SkillRepository> { SkillRepositoryImpl(get()) }
}