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

    override suspend fun getWorkingExperience(id: Int) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.workingExperienceDao().getById(id))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun getAllWorkingExperience() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.workingExperienceDao().getAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveWorkingExperience(data: WorkingExperienceEntity) =
        withContext(Dispatchers.IO) {
            try {
                return@withContext Result.Success(db.workingExperienceDao().insert(data))
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun saveAllWorkingExperience(list: List<WorkingExperienceEntity>) =
        withContext(Dispatchers.IO) {
            try {
                return@withContext Result.Success(db.workingExperienceDao().insertAll(list))
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun updateWorkingExperience(data: WorkingExperienceEntity) =
        withContext(Dispatchers.IO) {
            try {
                return@withContext Result.Success(db.workingExperienceDao().update(data))
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.workingExperienceDao().deleteById(id))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.workingExperienceDao().deleteAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

}