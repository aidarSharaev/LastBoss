package com.horizonguard.projectflow.domain.repository

internal interface LoginRepository {

    suspend fun loginByEmail(email: String): Result<String>

    suspend fun verifyOtp(email: String, code: String): Result<Boolean>
}
