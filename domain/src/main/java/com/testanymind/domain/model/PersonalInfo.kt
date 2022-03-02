package com.testanymind.domain.model

import com.testanymind.domain.entity.PersonalInfoEntity

data class PersonalInfo(
    val name: String,
    val role: String,
    val careerObjective: String,
    val avatar: String,
    val mobile: String,
    val email: String,
    val address: String
) {

    fun toEntity() = PersonalInfoEntity(
        name = name,
        role = role,
        careerObjective = careerObjective,
        avatar = avatar,
        mobile = mobile,
        email = email,
        address = address
    )
}