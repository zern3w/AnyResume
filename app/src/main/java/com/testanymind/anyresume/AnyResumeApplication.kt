package com.testanymind.anyresume

import android.app.Application
import com.testanymind.presentation.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AnyResumeApplication : Application() {

    private val presentationModule = listOf(viewModelModules)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AnyResumeApplication)
            modules(presentationModule)
        }
    }
}