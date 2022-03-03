package com.testanymind.domain.usecase

import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.repository.EducationRepository


class UpdateEducationUseCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke(data: EducationEntity) =
        educationRepository.updateEducation(data)
}