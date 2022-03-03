package com.testanymind.domain.usecase

import com.testanymind.domain.repository.ProjectRepository


class GetAllProjectUseCase(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke() =
        projectRepository.getAllProject()
}