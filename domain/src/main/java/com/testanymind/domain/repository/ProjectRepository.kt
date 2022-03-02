package com.testanymind.domain.repository

interface ProjectRepository {

    suspend fun getProject(): Result<Any>

    suspend fun saveProject(): Result<Any>

}