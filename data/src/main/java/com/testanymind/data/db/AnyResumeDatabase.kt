package com.testanymind.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testanymind.data.db.converter.TechnologyUsedListConverter
import com.testanymind.data.db.dao.EducationDao
import com.testanymind.data.db.dao.ProjectDao
import com.testanymind.data.db.dao.SkillDao
import com.testanymind.data.db.dao.WorkingExperienceDao
import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.entity.SkillEntity
import com.testanymind.domain.entity.WorkingExperienceEntity

@Database(
    entities = [
        EducationEntity::class,
        SkillEntity::class,
        ProjectEntity::class,
        WorkingExperienceEntity::class
    ],
    version = 1
)

@TypeConverters(
    TechnologyUsedListConverter::class
)

abstract class AnyResumeDatabase : RoomDatabase() {

    abstract fun educationDao(): EducationDao
    abstract fun skillDao(): SkillDao
    abstract fun projectDao(): ProjectDao
    abstract fun workingExperienceDao(): WorkingExperienceDao

}
