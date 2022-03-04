package com.testanymind.domain.model

import com.testanymind.domain.entity.WorkingExperienceEntity

data class WorkingExperience(
    val _id: Int = -1,
    val companyName: String,
    val logo: String,
    val role: String,
    val startDate: String,
    val endDate: String
) {
    fun getDuration(): String {
        return "$startDate - $endDate"
    }

    fun toEntity() = WorkingExperienceEntity(
        companyName = companyName,
        logo = logo,
        role = role,
        startDate = startDate,
        endDate = endDate
    )
}