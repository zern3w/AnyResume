package com.testanymind.domain.model

import com.testanymind.domain.entity.SkillEntity

data class Skill(
    val skill: String
) {

    fun toEntity() = SkillEntity(
        skill = skill
    )
}