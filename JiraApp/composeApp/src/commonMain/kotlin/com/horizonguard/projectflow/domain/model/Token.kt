package com.horizonguard.projectflow.domain.model

internal data class Token(
    val refresh: String,
    val access: String,
)
