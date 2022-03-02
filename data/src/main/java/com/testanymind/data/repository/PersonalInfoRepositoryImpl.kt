package com.testanymind.data.repository

import com.testanymind.data.db.AnyResumeDatabase
import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.PersonalInfoEntity
import com.testanymind.domain.repository.PersonalInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonalInfoRepositoryImpl(
    private val db: AnyResumeDatabase
) : PersonalInfoRepository {

    override suspend fun getPersonalInfo() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.personalInfoDao().get())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun savePersonalInfo(info: PersonalInfoEntity) = withContext(Dispatchers.IO) {
        try {
            db.personalInfoDao().insert(info)
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deletePersonalInfo() = withContext(Dispatchers.IO) {
        try {
            db.personalInfoDao().deleteAll()
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}