package com.testanymind.data.repository

import com.testanymind.data.db.AnyResumeDatabase
import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.repository.EducationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EducationRepositoryImpl(
    private val db: AnyResumeDatabase
) : EducationRepository {

    override suspend fun getEducation() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.educationDao().getAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveEducation(list: List<EducationEntity>) = withContext(Dispatchers.IO) {
        try {
            db.educationDao().insertAll(list)
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        try {
            db.educationDao().deleteAll()
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

}