package com.horizonguard.projectflow.data.iteractor

import com.horizonguard.projectflow.data.getValue
import com.horizonguard.projectflow.data.model.AuthBody
import com.horizonguard.projectflow.data.model.toToken
import com.horizonguard.projectflow.data.resultFailure
import com.horizonguard.projectflow.data.successApiCallResult
import com.horizonguard.projectflow.domain.iteractor.OtpUseCase
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import com.horizonguard.projectflow.utils.AppException

internal class OtpUseCaseImpl(
    private val preferenceRepository: PreferenceRepository,
): OtpUseCase {

    override fun invoke(code: String) {
        /*return try {
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
        }*/
    }
}