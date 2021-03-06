package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testanymind.domain.model.PersonalInfo

@Entity(tableName = "personal_info")
data class PersonalInfoEntity(
    val name: String,
    val role: String,
    val careerObjective: String,
    val avatar: String,
    val mobile: String,
    val email: String,
    val address: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toPersonalInfo() = PersonalInfo(
        name = name,
        role = role,
        careerObjective = careerObjective,
        avatar = avatar,
        mobile = mobile,
        email = email,
        address = address
    )
}