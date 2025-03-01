package com.horizonguard.projectflow.data.repository

import com.horizonguard.projectflow.data.failure
import com.horizonguard.projectflow.data.getValue
import com.horizonguard.projectflow.data.model.OtpBody
import com.horizonguard.projectflow.data.resultFailure
import com.horizonguard.projectflow.domain.remote.LoginApi
import com.horizonguard.projectflow.domain.repository.LoginRepository
import com.horizonguard.projectflow.domain.repository.PreferenceRepository

internal class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val preferenceRepository: PreferenceRepository,
) : LoginRepository {

    override suspend fun loginByEmail(
        email: String,
    ): Result<String> {
        return commonCatch {
            loginApi.loginByEmail(email)
        }
    }

    override suspend fun verifyOtp(
        email: String,
        code: String,
    ): Result<Boolean> {
        return commonCatch {
            val requestBody = OtpBody(email, code)
            val result = loginApi.verifyOtp(requestBody)
            result.getValue()?.let { body ->
                preferenceRepository.saveMainInformation(body)
                Result.success(true)
            } ?: result.resultFailure()
        }
    }

    private suspend fun <T> commonCatch(
        lambda: suspend () -> Result<T>,
    ): Result<T> {
        return try {
            lambda()
        } catch (e: Exception) {
            failure()
        }
    }
}
