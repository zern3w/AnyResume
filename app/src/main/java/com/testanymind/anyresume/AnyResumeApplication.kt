package com.testanymind.anyresume

import android.app.Application
import com.testanymind.data.di.databaseModule
import com.testanymind.data.di.repositoryModule
import com.testanymind.data.di.useCaseModule
import com.testanymind.presentation.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AnyResumeApplication : Application() {

    private val presentationModule = listOf(viewModelModules)
    private val dataModule = listOf(useCaseModule, repositoryModule, databaseModule)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AnyResumeApplication)
            modules(presentationModule + dataModule)
        }
    }
}