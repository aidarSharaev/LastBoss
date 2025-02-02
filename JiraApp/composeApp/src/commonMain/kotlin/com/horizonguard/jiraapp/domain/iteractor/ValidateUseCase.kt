package com.horizonguard.jiraapp.domain.iteractor

internal interface ValidateUseCase {

    suspend operator fun invoke(): Result<Boolean>
}