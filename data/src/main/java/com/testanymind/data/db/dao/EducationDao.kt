package com.testanymind.data.db.dao

import androidx.room.*
import com.testanymind.domain.entity.EducationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EducationDao {

    @Query("SELECT * FROM education WHERE id=:id")
    fun getById(id: Int): Flow<EducationEntity>

    @Query("SELECT * FROM education")
    fun getAll(): Flow<List<EducationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(education: EducationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(educations: List<EducationEntity>)

    @Update
    fun update(education: EducationEntity)

    @Query("DELETE FROM education WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM education")
    fun deleteAll()
}
