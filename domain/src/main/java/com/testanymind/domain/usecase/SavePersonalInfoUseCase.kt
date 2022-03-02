package com.testanymind.domain.usecase

import com.testanymind.domain.entity.PersonalInfoEntity
import com.testanymind.domain.repository.PersonalInfoRepository


class SavePersonalInfoUseCase(private val personalInfoRepository: PersonalInfoRepository) {

    suspend operator fun invoke(info: PersonalInfoEntity) =
        personalInfoRepository.savePersonalInfo(info)
}