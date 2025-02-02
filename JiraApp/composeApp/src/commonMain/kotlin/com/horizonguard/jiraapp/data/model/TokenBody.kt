package com.horizonguard.jiraapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthBody(
    val email: String,
    val access_token: String,
    val refresh_token: String,
)