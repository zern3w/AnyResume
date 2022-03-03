package com.testanymind.domain.usecase

import com.testanymind.domain.repository.EducationRepository


class DeleteEducationUseCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke(id: Int) =
        educationRepository.delete(id)
}