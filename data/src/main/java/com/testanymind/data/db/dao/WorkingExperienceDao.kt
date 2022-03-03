package com.testanymind.data.db.dao

import androidx.room.*
import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.entity.WorkingExperienceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkingExperienceDao {

    @Query("SELECT * FROM working_experience WHERE id=:id")
    fun getById(id: Int): Flow<WorkingExperienceEntity>

    @Query("SELECT * FROM working_experience")
    fun getAll(): Flow<List<WorkingExperienceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workExp: WorkingExperienceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(workExps: List<WorkingExperienceEntity>)

    @Update
    fun update(education: WorkingExperienceEntity)

    @Query("DELETE FROM working_experience WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM working_experience")
    fun deleteAll()
}
