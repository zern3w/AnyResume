package com.testanymind.data.repository

import com.testanymind.domain.repository.ProjectRepository

class ProjectRepositoryImpl(

) : ProjectRepository {
    override suspend fun getProject(): Result<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun saveProject(): Result<Any> {
        TODO("Not yet implemented")
    }

}