package com.testanymind.data.di

import com.testanymind.domain.usecase.GetSkillsUseCase
import com.testanymind.domain.usecase.SaveSkillsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetSkillsUseCase(get()) }
    single { SaveSkillsUseCase(get()) }
}