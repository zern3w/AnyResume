package com.testanymind.domain.model

import com.testanymind.domain.entity.WorkingExperienceEntity

data class WorkingExperience(
    val companyName: String,
    val logo: String,
    val role: String,
    val startDate: String,
    val endDate: String
) {
    fun getDuration(): String {
        return "Mar 2017 - Dec 2021"
    }

    fun toEntity() = WorkingExperienceEntity(
        companyName = companyName,
        logo = logo,
        role = role,
        startDate = startDate,
        endDate = endDate
    )
}