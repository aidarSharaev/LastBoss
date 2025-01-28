package com.horizonguard.jiraapp.domain.repository

import com.horizonguard.jiraapp.domain.model.Token

internal interface TokenRepository {

    fun validateUser(): Result<Token>
}