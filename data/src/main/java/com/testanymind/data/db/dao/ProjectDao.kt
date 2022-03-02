package com.testanymind.data.db.dao

import androidx.room.*
import com.testanymind.domain.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project")
    fun getAll(): Flow<List<ProjectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(skills: List<ProjectEntity>)

    @Delete
     fun delete(skill: ProjectEntity)
}
