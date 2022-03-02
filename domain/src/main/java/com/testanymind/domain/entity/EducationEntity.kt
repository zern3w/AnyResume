package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testanymind.domain.model.Education

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

    fun toEducation() = Education(
        schoolName = schoolName,
        logo = logo,
        _class = _class,
        passingYear = passingYear,
        gpa = gpa
    )
}