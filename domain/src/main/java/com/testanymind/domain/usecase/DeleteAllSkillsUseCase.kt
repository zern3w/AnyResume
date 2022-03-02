package com.testanymind.domain.usecase

import com.testanymind.domain.repository.SkillRepository


class DeleteAllSkillsUseCase(private val skillRepository: SkillRepository) {

    suspend operator fun invoke() = skillRepository.deleteAll()
}