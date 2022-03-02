package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
data class ProjectEntity(
    val projectName: String,
    val logo: String,
    val teamSize: Int,
    val projectSummary: String,
    val technologyUsed: List<String>,
    val role: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}