package com.horizonguard.jiraapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.horizonguard.jiraapp.domain.model.Token
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository

internal class PreferenceRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : PreferenceRepository {

    override fun validateUser(): Result<Token> {
        TODO("Not yet implemented")
    }
}