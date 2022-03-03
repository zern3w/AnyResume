package com.testanymind.domain.usecase

import com.testanymind.domain.repository.ProjectRepository


class DeleteProjectUseCase(private val projectRepository: ProjectRepository) {

    suspend operator fun invoke(id: Int) =
        projectRepository.delete(id)
}