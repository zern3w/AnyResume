package com.testanymind.domain.repository

import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.SkillEntity
import kotlinx.coroutines.flow.Flow

interface SkillRepository {

    suspend fun getSkills(): Result<Flow<List<SkillEntity>>>

    suspend fun saveSkills(list: List<String>): Result<Unit>

}