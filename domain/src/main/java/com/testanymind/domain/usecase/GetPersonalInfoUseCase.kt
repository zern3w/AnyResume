package com.testanymind.domain.usecase

import com.testanymind.domain.repository.PersonalInfoRepository


class GetPersonalInfoUseCase(private val personalInfoRepository: PersonalInfoRepository) {

    suspend operator fun invoke() =
        personalInfoRepository.getPersonalInfo()
}