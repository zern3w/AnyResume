package com.testanymind.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testanymind.domain.model.Skill

@Entity(tableName = "skill")
data class SkillEntity(
    val skill: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toSkill() = Skill(skill = skill)

}