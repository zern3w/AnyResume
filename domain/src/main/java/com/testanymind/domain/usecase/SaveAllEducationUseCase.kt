package com.testanymind.domain.usecase

import com.testanymind.domain.entity.EducationEntity
import com.testanymind.domain.repository.EducationRepository


class SaveAllEducationUseCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke(list: List<EducationEntity>) =
        educationRepository.saveAllEducation(list)
}