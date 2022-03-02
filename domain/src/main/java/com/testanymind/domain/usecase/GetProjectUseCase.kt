package com.testanymind.domain.usecase

import com.testanymind.domain.repository.ProjectRepository


class GetProjectUseCase(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke() =
        projectRepository.getProject()
}