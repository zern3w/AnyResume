package com.testanymind.data.repository

import com.testanymind.data.db.AnyResumeDatabase
import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.SkillEntity
import com.testanymind.domain.repository.SkillRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SkillRepositoryImpl(
    private val db: AnyResumeDatabase
) : SkillRepository {

    override suspend fun getSkills() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.skillDao().getAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveSkills(list: List<SkillEntity>) = withContext(Dispatchers.IO) {
        try {
            db.skillDao().insertAll(list)
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        try {
            db.skillDao().deleteAll()
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}