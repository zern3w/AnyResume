package com.testanymind.domain.usecase

import com.testanymind.domain.repository.EducationRepository


class GetEducationUseCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke() =
        educationRepository.getEducation()
}