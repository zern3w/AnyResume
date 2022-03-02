package com.testanymind.data.db.dao

import androidx.room.*
import com.testanymind.domain.entity.PersonalInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalInfoDao {
    @Query("SELECT * FROM personal_info LIMIT 1")
    fun get(): Flow<PersonalInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(info: PersonalInfoEntity)

    @Query("DELETE FROM personal_info")
    fun deleteAll()
}
