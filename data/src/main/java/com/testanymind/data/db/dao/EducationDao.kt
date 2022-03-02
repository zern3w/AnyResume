package com.testanymind.data.db.dao

import androidx.room.*
import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.entity.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EducationDao {
    @Query("SELECT * FROM education")
    fun getAll(): Flow<List<EducationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(skills: List<EducationEntity>)

    @Query("DELETE FROM education")
    fun deleteAll()
}
