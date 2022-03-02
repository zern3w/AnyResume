package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal_info")
data class PersonalInfoEntity(
    val name: String,
    val role: String,
    val avatar: String,
    val mobile: String,
    val email: String,
    val address: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}