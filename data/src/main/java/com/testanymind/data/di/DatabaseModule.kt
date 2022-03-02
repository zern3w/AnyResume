package com.testanymind.data.di

import androidx.room.Room
import com.testanymind.data.db.AnyResumeDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "anyresume-database"

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AnyResumeDatabase::class.java, DATABASE_NAME)
            .build()
    }

}