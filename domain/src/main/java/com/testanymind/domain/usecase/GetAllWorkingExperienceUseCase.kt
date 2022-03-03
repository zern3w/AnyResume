package com.testanymind.domain.usecase

import com.testanymind.domain.repository.WorkingExperienceRepository


class GetAllWorkingExperienceUseCase(private val workingExperienceRepository: WorkingExperienceRepository) {

    suspend operator fun invoke() =
        workingExperienceRepository.getAllWorkingExperience()
}