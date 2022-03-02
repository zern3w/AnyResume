package com.testanymind.domain.repository

import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.common.Result
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    suspend fun getProject(): Result<Flow<List<ProjectEntity>>>

    suspend fun saveProject(list: List<ProjectEntity>): Result<Unit>

    suspend fun deleteAll(): Result<Unit>

}