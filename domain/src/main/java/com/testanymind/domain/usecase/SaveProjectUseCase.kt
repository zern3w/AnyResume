package com.testanymind.domain.usecase

import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.repository.ProjectRepository


class SaveProjectUseCase(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke(data: ProjectEntity) =
        projectRepository.saveProject(data)
}