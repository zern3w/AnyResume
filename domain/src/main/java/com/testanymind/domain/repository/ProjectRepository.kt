package com.testanymind.domain.repository

import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.EducationEntity
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    suspend fun getProject(id: Int): Result<Flow<ProjectEntity>>

    suspend fun getAllProject(): Result<Flow<List<ProjectEntity>>>

    suspend fun saveProject(list: ProjectEntity): Result<Unit>

    suspend fun saveAllProject(list: List<ProjectEntity>): Result<Unit>

    suspend fun updateProject(data: ProjectEntity): Result<Unit>

    suspend fun delete(id: Int): Result<Unit>

    suspend fun deleteAll(): Result<Unit>

}