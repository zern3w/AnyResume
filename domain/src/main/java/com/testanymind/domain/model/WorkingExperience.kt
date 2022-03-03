package com.testanymind.domain.model

import androidx.room.PrimaryKey
import com.testanymind.domain.entity.WorkingExperienceEntity

data class WorkingExperience(
    val _id: Int = -1,
    val companyName: String,
    val logo: String,
    val role: String,
    val startDate: String,
    val endDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

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