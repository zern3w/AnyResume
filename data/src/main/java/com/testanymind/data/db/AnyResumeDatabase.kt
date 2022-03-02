package com.testanymind.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testanymind.data.db.converter.TechnologyUsedListConverter
import com.testanymind.data.db.dao.*
import com.testanymind.domain.entity.*

@Database(
    entities = [
        PersonalInfoEntity::class,
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

    abstract fun personalInfoDao(): PersonalInfoDao
    abstract fun educationDao(): EducationDao
    abstract fun skillDao(): SkillDao
    abstract fun projectDao(): ProjectDao
    abstract fun workingExperienceDao(): WorkingExperienceDao

}
