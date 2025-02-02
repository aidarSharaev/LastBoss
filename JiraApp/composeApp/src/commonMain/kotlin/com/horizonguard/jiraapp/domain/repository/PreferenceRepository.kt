package com.horizonguard.jiraapp.domain.repository

import com.horizonguard.jiraapp.domain.model.Token

internal interface PreferenceRepository {

    suspend fun readToken(): Token?
    suspend fun saveToken(token: Token)

    suspend fun readEmail(): String
    suspend fun saveEmail(email: String)
}
