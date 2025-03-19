package com.horizonguard.projectflow.domain.remote

import com.horizonguard.projectflow.data.model.AuthBody
import com.horizonguard.projectflow.data.model.OtpBody
import com.horizonguard.projectflow.data.model.SignUpBody

internal interface LoginApi {

    suspend fun authUser(body: AuthBody): Result<AuthBody>

    suspend fun loginByEmail(email: String): Result<String>

    suspend fun verifyOtp(otpBody: OtpBody): Result<AuthBody>

    suspend fun createNewAccount(signUpBody: SignUpBody): Result<String>
}

 const val URL = "https://horizonguard.tech/v2/"

