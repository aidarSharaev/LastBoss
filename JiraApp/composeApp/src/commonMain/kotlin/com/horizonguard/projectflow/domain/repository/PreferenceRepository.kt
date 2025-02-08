package com.horizonguard.projectflow.domain.repository

import com.horizonguard.projectflow.domain.model.Token

internal interface PreferenceRepository {

    suspend fun readToken(): Token?
    suspend fun saveToken(token: Token)

    suspend fun readEmail(): String
    suspend fun saveEmail(email: String)
}
