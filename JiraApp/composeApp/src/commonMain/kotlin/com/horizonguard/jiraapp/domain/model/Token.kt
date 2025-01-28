package com.horizonguard.jiraapp.domain.model

internal data class Token(
    val refresh: String,
    val access: String,
)
