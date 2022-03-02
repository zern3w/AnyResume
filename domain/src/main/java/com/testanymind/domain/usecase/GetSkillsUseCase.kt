package com.testanymind.domain.usecase

import com.testanymind.domain.repository.SkillRepository


class GetSkillsUseCase(private val skillRepository: SkillRepository) {

    suspend operator fun invoke() = skillRepository.getSkills()
}