package com.testanymind.domain.usecase

import com.testanymind.domain.repository.SkillRepository


class SaveSkillsUseCase(private val skillRepository: SkillRepository) {

    suspend operator fun invoke(list: List<String>) = skillRepository.saveSkills(list)
}