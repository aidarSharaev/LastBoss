package com.horizonguard.jiraapp.data.iteractor

import com.horizonguard.jiraapp.domain.iteractor.ValidateUseCase
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository
import com.horizonguard.jiraapp.utils.TokenIsNull

internal class ValidateUseCaseImpl(
    private val preferenceRepository: PreferenceRepository,

) : ValidateUseCase {

    override suspend fun invoke() : Result<Boolean> {
        return preferenceRepository.readToken()?.let {
            Result.success(true)
        } ?: Result.failure(TokenIsNull())
    }
}