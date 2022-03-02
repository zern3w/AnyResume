package com.testanymind.domain.usecase

import com.testanymind.domain.repository.PersonalInfoRepository


class DeleteAllPersonalInfoUseCase(private val personalInfoRepository: PersonalInfoRepository) {

    suspend operator fun invoke() =
        personalInfoRepository.deletePersonalInfo()
}