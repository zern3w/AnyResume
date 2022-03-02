package com.testanymind.data.db.dao

import androidx.room.*
import com.testanymind.domain.entity.WorkingExperienceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkingExperienceDao {
    @Query("SELECT * FROM working_experience")
    fun getAll(): Flow<List<WorkingExperienceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(skills: List<WorkingExperienceEntity>)

    @Query("DELETE FROM working_experience")
    fun deleteAll()
}
