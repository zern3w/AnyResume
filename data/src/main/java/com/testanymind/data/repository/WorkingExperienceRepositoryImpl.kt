package com.testanymind.data.repository

import com.testanymind.data.db.AnyResumeDatabase
import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.WorkingExperienceEntity
import com.testanymind.domain.repository.WorkingExperienceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkingExperienceRepositoryImpl(
    private val db: AnyResumeDatabase
) : WorkingExperienceRepository {

    override suspend fun getWorkingExperience() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.workingExperienceDao().getAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveWorkingExperience(list: List<WorkingExperienceEntity>) =
        withContext(Dispatchers.IO) {
            try {
                db.workingExperienceDao().insertAll(list)
                return@withContext Result.Success(Unit)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        try {
            db.workingExperienceDao().deleteAll()
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

}