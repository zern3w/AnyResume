package com.testanymind.domain.usecase

import com.testanymind.domain.entity.WorkingExperienceEntity
import com.testanymind.domain.repository.SkillRepository
import com.testanymind.domain.repository.WorkingExperienceRepository


class SaveWorkingExperienceUseCase(private val workingExperienceRepository: WorkingExperienceRepository) {

    suspend operator fun invoke(list: List<WorkingExperienceEntity>) = workingExperienceRepository.saveWorkingExperience(list)
}