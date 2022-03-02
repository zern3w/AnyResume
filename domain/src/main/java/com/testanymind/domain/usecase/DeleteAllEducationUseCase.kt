package com.testanymind.domain.usecase

import com.testanymind.domain.repository.EducationRepository


class DeleteAllEducationUseCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke() = educationRepository.deleteAll()
}