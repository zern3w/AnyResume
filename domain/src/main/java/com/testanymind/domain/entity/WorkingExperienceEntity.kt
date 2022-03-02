package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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
}