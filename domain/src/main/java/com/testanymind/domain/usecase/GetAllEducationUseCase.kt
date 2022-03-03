package com.testanymind.domain.usecase

import com.testanymind.domain.repository.EducationRepository


class GetAllEducationUseCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke() =
        educationRepository.getAllEducation()
}