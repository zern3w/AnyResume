package com.testanymind.domain.repository

import com.testanymind.domain.entity.EducationEntity
import kotlinx.coroutines.flow.Flow
import com.testanymind.domain.common.Result

interface EducationRepository {

    suspend fun getEducation(): Result<Flow<List<EducationEntity>>>

    suspend fun saveEducation(list: List<EducationEntity>): Result<Unit>

    suspend fun deleteAll(): Result<Unit>

}