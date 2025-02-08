package com.horizonguard.projectflow.data.iteractor

import com.horizonguard.projectflow.data.ApiCall
import com.horizonguard.projectflow.data.getValue
import com.horizonguard.projectflow.data.model.AuthBody
import com.horizonguard.projectflow.data.model.toToken
import com.horizonguard.projectflow.data.resultFailure
import com.horizonguard.projectflow.data.successApiCallResult
import com.horizonguard.projectflow.domain.iteractor.ValidateUseCase
import com.horizonguard.projectflow.domain.remote.LoginApi
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import com.horizonguard.projectflow.utils.AppException

internal class ValidateUseCaseImpl(
    private val preferenceRepository: PreferenceRepository,
    private val loginApi: LoginApi,
) : ValidateUseCase {

    override suspend fun invoke(): Result<ApiCall> {
        return try {
            return preferenceRepository.readToken()?.let { token ->
                val email = preferenceRepository.readEmail()
                if (email.isEmpty()) {
                    failure(AppException.EmailIsNull())
                }
                val authBody = AuthBody(
                    email = email,
                    access_token = token.access,
                    refresh_token = token.refresh,
                )
                val result: Result<AuthBody> = loginApi.validateToken(authBody)
                result.getValue()?.let { body ->
                    preferenceRepository.saveEmail(body.email)
                    preferenceRepository.saveToken(body.toToken())
                    successApiCallResult
                } ?: result.resultFailure()
            } ?: failure(AppException.TokenIsNull())
        } catch (e: Exception) {
            failure()
        }
    }
}

private fun failure(
    exc: Throwable = AppException.CommonException(),
): Result<ApiCall> {
    return Result.failure(exc)
}
