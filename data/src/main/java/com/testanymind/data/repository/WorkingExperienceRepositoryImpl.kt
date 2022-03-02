package com.testanymind.data.repository

import com.testanymind.domain.repository.EducationRepository
import com.testanymind.domain.repository.SkillRepository
import com.testanymind.domain.repository.WorkingExperienceRepository

class WorkingExperienceRepositoryImpl(

) : WorkingExperienceRepository {
    override suspend fun getWorkingExperience(): Result<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun saveWorkingExperience(): Result<Any> {
        TODO("Not yet implemented")
    }

}