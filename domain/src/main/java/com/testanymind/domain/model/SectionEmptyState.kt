package com.testanymind.domain.model


data class SectionEmptyState(
    val sectionTypeId: Int,
    val isEmpty: Boolean
) {

    companion object {
        const val SECTION_EDUCATION = 1
        const val SECTION_SKILL = 2
        const val SECTION_EXPERIENCE = 3
        const val SECTION_PROJECT = 4
    }
}