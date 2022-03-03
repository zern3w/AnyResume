package com.testanymind.domain.usecase

import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.repository.ProjectRepository


class SaveAllProjectUseCase(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke(list: List<ProjectEntity>) =
        projectRepository.saveAllProject(list)
}