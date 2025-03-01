package com.horizonguard.projectflow.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.horizonguard.projectflow.data.model.AuthBody
import com.horizonguard.projectflow.data.model.toToken
import com.horizonguard.projectflow.domain.model.Token
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val ACCESS_NAME = "ACCESS_NAME"
private const val REFRESH_NAME = "ACCESS_NAME"
private const val EMAIL_NAME = "EMAIL_NAME"

internal class PreferenceRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : PreferenceRepository {

    private val _accessKey = stringPreferencesKey(name = ACCESS_NAME)
    private val _refreshKey = stringPreferencesKey(name = REFRESH_NAME)
    private val _emailKey = stringPreferencesKey(name = EMAIL_NAME)

    override suspend fun saveMainInformation(body: AuthBody) {
        saveToken(body.toToken())
        saveEmail(body.email)
    }

    override suspend fun readToken(): Token? {
        val access = dataStore.data.map { it[_accessKey] }.first()
        val refresh = dataStore.data.map { it[_refreshKey] }.first()

        return if (refresh != null && access != null) {
            Token(access = access, refresh = refresh)
        } else {
            null
        }
    }

    override suspend fun saveToken(token: Token) {
        dataStore.edit { pref ->
            pref.set(key = _accessKey, value = token.access)
            pref.set(key = _refreshKey, value = token.refresh)
        }
    }

    override suspend fun readEmail(): String {
        return dataStore.data.map { it[_emailKey] }.first() ?: ""
    }

    override suspend fun saveEmail(email: String) {
        dataStore.edit { pref ->
            pref.set(key = _emailKey, value = email)
        }
    }

    override suspend fun resetEmail() {
        saveEmail("")
    }
}
