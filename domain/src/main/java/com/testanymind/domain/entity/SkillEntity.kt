package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skill")
data class SkillEntity(
    val skill: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        fun fromString(s: String): SkillEntity {
            return SkillEntity(s)
        }
    }
}