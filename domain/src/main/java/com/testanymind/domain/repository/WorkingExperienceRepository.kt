package com.testanymind.domain.repository

import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.WorkingExperienceEntity
import kotlinx.coroutines.flow.Flow

interface WorkingExperienceRepository {

    suspend fun getWorkingExperience(): Result<Flow<List<WorkingExperienceEntity>>>

    suspend fun saveWorkingExperience(list: List<WorkingExperienceEntity>): Result<Unit>

    suspend fun deleteAll(): Result<Unit>

}