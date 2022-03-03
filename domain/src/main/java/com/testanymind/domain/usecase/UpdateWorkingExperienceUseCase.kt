package com.testanymind.domain.usecase

import com.testanymind.domain.entity.WorkingExperienceEntity
import com.testanymind.domain.repository.WorkingExperienceRepository


class UpdateWorkingExperienceUseCase(private val workingExperienceRepository: WorkingExperienceRepository) {

    suspend operator fun invoke(data: WorkingExperienceEntity) =
        workingExperienceRepository.updateWorkingExperience(data)
}