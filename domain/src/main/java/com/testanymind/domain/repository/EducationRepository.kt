package com.testanymind.domain.repository

interface EducationRepository {

    suspend fun getEducation(): Result<Any>

    suspend fun saveEducation(): Result<Any>

}