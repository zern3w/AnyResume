package com.testanymind.domain.model

import com.testanymind.domain.entity.ProjectEntity

data class ProjectDetail(
    val projectName: String,
    val logo: String,
    val teamSize: Int,
    val projectSummary: String,
    val technologyUsed: List<String>,
    val role: String
) {

    fun toEntity() = ProjectEntity(
        projectName = projectName,
        logo = logo,
        teamSize = teamSize,
        projectSummary = projectSummary,
        technologyUsed = technologyUsed,
        role = role
    )
}