package com.testanymind.data.db.dao

import androidx.room.*
import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query("SELECT * FROM project WHERE id=:id")
    fun getById(id: Int): Flow<ProjectEntity>

    @Query("SELECT * FROM project")
    fun getAll(): Flow<List<ProjectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(project: ProjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(projects: List<ProjectEntity>)

    @Update
    fun update(project: ProjectEntity)

    @Query("DELETE FROM project WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM project")
    fun deleteAll()
}