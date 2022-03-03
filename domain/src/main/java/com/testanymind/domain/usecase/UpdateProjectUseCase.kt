package com.testanymind.domain.usecase

import com.testanymind.domain.entity.ProjectEntity
import com.testanymind.domain.repository.ProjectRepository


class UpdateProjectUseCase(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke(data: ProjectEntity) =
        projectRepository.updateProject(data)
}