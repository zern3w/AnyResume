package com.testanymind.domain.usecase

import com.testanymind.domain.repository.WorkingExperienceRepository


class DeleteWorkingExperienceUseCase(private val workingExperienceRepository: WorkingExperienceRepository) {

    suspend operator fun invoke(id: Int) =
        workingExperienceRepository.delete(id)
}