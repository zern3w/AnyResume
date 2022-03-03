package com.testanymind.domain.repository

import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.entity.WorkingExperienceEntity
import kotlinx.coroutines.flow.Flow

interface WorkingExperienceRepository {

    suspend fun getWorkingExperience(id: Int): Result<Flow<WorkingExperienceEntity>>

    suspend fun getAllWorkingExperience(): Result<Flow<List<WorkingExperienceEntity>>>

    suspend fun saveWorkingExperience(data: WorkingExperienceEntity): Result<Unit>

    suspend fun saveAllWorkingExperience(list: List<WorkingExperienceEntity>): Result<Unit>

    suspend fun updateWorkingExperience(data: WorkingExperienceEntity): Result<Unit>

    suspend fun delete(id: Int): Result<Unit>

    suspend fun deleteAll(): Result<Unit>

}