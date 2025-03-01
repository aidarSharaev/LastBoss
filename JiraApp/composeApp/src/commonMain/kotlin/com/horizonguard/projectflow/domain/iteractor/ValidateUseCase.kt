package com.horizonguard.projectflow.domain.iteractor

internal interface ValidateUseCase {

    suspend operator fun invoke(): Result<Boolean>
}
