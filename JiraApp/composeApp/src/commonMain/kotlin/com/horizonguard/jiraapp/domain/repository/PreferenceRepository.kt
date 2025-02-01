package com.horizonguard.jiraapp.domain.repository

import com.horizonguard.jiraapp.domain.model.Token

internal interface PreferenceRepository {

    fun validateUser(): Result<Token>
}