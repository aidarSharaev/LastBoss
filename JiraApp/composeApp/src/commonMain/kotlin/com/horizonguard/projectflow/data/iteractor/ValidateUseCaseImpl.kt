package com.horizonguard.projectflow.data.iteractor

import com.horizonguard.projectflow.data.failure
import com.horizonguard.projectflow.data.getValue
import com.horizonguard.projectflow.data.model.AuthBody
import com.horizonguard.projectflow.data.model.toToken
import com.horizonguard.projectflow.data.resultFailure
import com.horizonguard.projectflow.domain.iteractor.ValidateUseCase
import com.horizonguard.projectflow.domain.remote.LoginApi
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import com.horizonguard.projectflow.utils.AppException

internal class ValidateUseCaseImpl(
    private val preferenceRepository: PreferenceRepository,
    private val loginApi: LoginApi,
) : ValidateUseCase {

    override suspend fun invoke(): Result<Boolean> {
        return try {
            preferenceRepository.readToken()?.let { token ->
                val email = preferenceRepository.readEmail()
                if (email.isEmpty()) {
                    failure()
                } else {
                    val authBody = AuthBody(
                        email = email,
                        access_token = token.access,
                        refresh_token = token.refresh,
                    )
                    val result: Result<AuthBody> = loginApi.authUser(authBody)
                    result.getValue()?.let { body ->
                        preferenceRepository.saveMainInformation(body)
                        Result.success(true)
                    } ?: result.resultFailure()
                }
            } ?: Result.success(false)
        } catch (e: Exception) {
            failure()
        }
    }
}
