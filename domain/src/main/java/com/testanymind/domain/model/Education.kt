package com.testanymind.domain.model

import com.testanymind.domain.entity.EducationEntity

data class Education(
    val _id: Int = -1,
    val schoolName: String,
    val logo: String,
    val _class: String,
    val passingYear: String,
    val gpa: Double
) {
    fun toEntity() = EducationEntity(
        schoolName = schoolName,
        logo = logo,
        _class = _class,
        passingYear = passingYear,
        gpa = gpa
    )
}