package com.testanymind.data.repository

import com.testanymind.data.db.AnyResumeDatabase
import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepositoryImpl(
    private val db: AnyResumeDatabase
) : ProjectRepository {

    override suspend fun getProject() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().getAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveProject(list: List<ProjectEntity>) = withContext(Dispatchers.IO) {
        try {
            db.projectDao().insertAll(list)
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        try {
            db.projectDao().deleteAll()
            return@withContext Result.Success(Unit)
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

}