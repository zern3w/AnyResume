package com.testanymind.domain.usecase

import com.testanymind.domain.repository.WorkingExperienceRepository


class GetWorkingExperienceUseCase(private val workingExperienceRepository: WorkingExperienceRepository) {

    suspend operator fun invoke(id: Int) =
        workingExperienceRepository.getWorkingExperience(id)
}