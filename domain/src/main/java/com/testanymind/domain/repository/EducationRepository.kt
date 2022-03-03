package com.testanymind.domain.repository

import com.testanymind.domain.entity.EducationEntity
import kotlinx.coroutines.flow.Flow
import com.testanymind.domain.common.Result

interface EducationRepository {

    suspend fun getEducation(id: Int): Result<Flow<EducationEntity>>

    suspend fun getAllEducation(): Result<Flow<List<EducationEntity>>>

    suspend fun saveEducation(data: EducationEntity): Result<Unit>

    suspend fun saveAllEducation(list: List<EducationEntity>): Result<Unit>

    suspend fun updateEducation(data: EducationEntity): Result<Unit>

    suspend fun delete(id: Int): Result<Unit>

    suspend fun deleteAll(): Result<Unit>

}