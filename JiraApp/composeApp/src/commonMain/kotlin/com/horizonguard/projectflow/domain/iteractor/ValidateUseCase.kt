package com.horizonguard.projectflow.domain.iteractor

import com.horizonguard.projectflow.data.ApiCall

internal interface ValidateUseCase {

    suspend operator fun invoke(): Result<ApiCall>
}