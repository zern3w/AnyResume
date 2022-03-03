package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testanymind.domain.model.WorkingExperience

@Entity(tableName = "working_experience")
data class WorkingExperienceEntity(
    val companyName: String,
    val logo: String,
    val role: String,
    val startDate: String,
    val endDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toWorkingExperience() = WorkingExperience(
        _id = id,
        companyName = companyName,
        logo = logo,
        role = role,
        startDate = startDate,
        endDate = endDate
    )
}