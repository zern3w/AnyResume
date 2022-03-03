package com.testanymind.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.entity.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {

    @Query("SELECT * FROM skill WHERE id=:id")
    fun getById(id: Int): Flow<SkillEntity>

    @Query("SELECT * FROM skill")
    fun getAll(): Flow<List<SkillEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(skill: SkillEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(skills: List<SkillEntity>)

    @Query("DELETE FROM skill WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM skill")
    fun deleteAll()
}
