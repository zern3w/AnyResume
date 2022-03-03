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

    override suspend fun getEducation(id: Int) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.educationDao().getById(id))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun getAllEducation() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.educationDao().getAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveEducation(data: EducationEntity) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.educationDao().insert(data))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveAllEducation(list: List<EducationEntity>) =
        withContext(Dispatchers.IO) {
            try {
                return@withContext Result.Success(db.educationDao().insertAll(list))
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun updateEducation(data: EducationEntity) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.educationDao().update(data))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.educationDao().deleteById(id))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.educationDao().deleteAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

}