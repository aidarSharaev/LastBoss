package com.horizonguard.projectflow.domain.iteractor

internal interface OtpUseCase {

    operator fun invoke(code: String)
}