package com.testanymind.domain.usecase

import com.testanymind.domain.repository.WorkingExperienceRepository


class DeleteAllWorkingExperienceUseCase(private val workingExperienceRepository: WorkingExperienceRepository) {

    suspend operator fun invoke() = workingExperienceRepository.deleteAll()
}