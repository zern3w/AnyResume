package com.testanymind.domain.repository

interface WorkingExperienceRepository {

    suspend fun getWorkingExperience(): Result<Any>

    suspend fun saveWorkingExperience(): Result<Any>

}