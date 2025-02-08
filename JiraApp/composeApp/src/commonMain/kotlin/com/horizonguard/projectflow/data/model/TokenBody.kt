package com.horizonguard.projectflow.data.model

import com.horizonguard.projectflow.domain.model.Token
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthBody(
    val email: String,
    val access_token: String,
    val refresh_token: String,
)

internal fun AuthBody.toToken() =
    Token(
        refresh = refresh_token,
        access = access_token,
    )