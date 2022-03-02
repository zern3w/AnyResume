package com.testanymind.domain.repository

import com.testanymind.domain.entity.PersonalInfoEntity
import kotlinx.coroutines.flow.Flow
import com.testanymind.domain.common.Result

interface PersonalInfoRepository {

    suspend fun getPersonalInfo(): Result<Flow<PersonalInfoEntity>>

    suspend fun savePersonalInfo(info: PersonalInfoEntity): Result<Unit>

    suspend fun deletePersonalInfo(): Result<Unit>

}