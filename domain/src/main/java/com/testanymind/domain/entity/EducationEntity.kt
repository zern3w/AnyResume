package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "education")
data class EducationEntity(
    val schoolName: String,
    val logo: String,
    val _class: String,
    val passingYear: String,
    val gpa: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}