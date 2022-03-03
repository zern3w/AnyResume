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

    override suspend fun getProject(id: Int) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().getById(id))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun getAllProject() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().getAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveProject(data: ProjectEntity) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().insert(data))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun saveAllProject(list: List<ProjectEntity>) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().insertAll(list))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun updateProject(data: ProjectEntity) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().update(data))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().deleteById(id))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        try {
            return@withContext Result.Success(db.projectDao().deleteAll())
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

}