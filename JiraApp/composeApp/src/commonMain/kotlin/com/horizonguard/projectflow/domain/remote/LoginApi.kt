package com.horizonguard.projectflow.domain.remote

import com.horizonguard.projectflow.data.model.AuthBody
import com.horizonguard.projectflow.data.model.OtpBody
import com.horizonguard.projectflow.data.model.SignUpBody
import com.horizonguard.projectflow.utils.AppException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.io.IOException

internal interface LoginApi {

    suspend fun authUser(body: AuthBody): Result<AuthBody>

    suspend fun loginByEmail(email: String): Result<String>

    suspend fun verifyOtp(otpBody: OtpBody): Result<AuthBody>

    suspend fun createNewAccount(signUpBody: SignUpBody): Result<String>
}

private const val URL = "http://0.0.0.0:8080/"

internal class LoginApiImpl : LoginApi {

    private val client = HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                }
            )
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(3)
        }
    }

    override suspend fun authUser(body: AuthBody): Result<AuthBody> {
        return commonCatch {
            /*val response = client.post("${URL}aa") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }*/
            val response = client.post("${URL}aa") {
                contentType(ContentType.Application.Json)
                setBody("body")
            }
            when (response.status.value) {
                in 200..299 -> {
                    val respBody: AuthBody =
                        response.body() as? AuthBody ?: throw ClassCastException("authUser")
                    Result.success(respBody)
                }

                400 -> {
                    Result.failure(AppException.BadRequestException())
                }

                else -> {
                    Result.failure(AppException.CommonException())
                }
            }
        }
    }

    override suspend fun loginByEmail(email: String): Result<String> {
        return commonCatch {
            val response = client.post("${URL}login") {
                contentType(ContentType.Application.Json)
                setBody(email)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val respBody: String =
                        response.body() as? String ?: throw ClassCastException("loginByEmail")
                    Result.success(respBody)
                }

                400 -> {
                    Result.failure(AppException.BadRequestException())
                }

                else -> {
                    Result.failure(AppException.CommonException())
                }
            }
        }
    }

    override suspend fun createNewAccount(signUpBody: SignUpBody): Result<String> {
        return commonCatch {
            val response = client.post("${URL}signup") {
                contentType(ContentType.Application.Json)
                setBody(signUpBody)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val respBody: String =
                        response.body() as? String ?: throw ClassCastException("createNewAccount")
                    Result.success(respBody)
                }

                400 -> {
                    Result.failure(AppException.BadRequestException())
                }

                else -> {
                    Result.failure(AppException.CommonException())
                }
            }
        }
    }

    override suspend fun verifyOtp(
        otpBody: OtpBody,
    ): Result<AuthBody> {
        return commonCatch {
            val response = client.post("$URL/verify") {
                contentType(ContentType.Application.Json)
                setBody(otpBody)
            }
            when (response.status.value) {
                in 200..299 -> {
                    val respBody: AuthBody =
                        response.body() as? AuthBody ?: throw ClassCastException("verifyOtp")
                    Result.success(respBody)
                }

                400 -> {
                    Result.failure(AppException.BadRequestException())
                }

                else -> {
                    Result.failure(AppException.CommonException())
                }
            }
        }
    }

    private suspend fun <T> commonCatch(
        lambda: suspend () -> Result<T>,
    ): Result<T> {
        return try {
            lambda()
        } catch (e: IOException) {
            Result.failure(AppException.IoException())
        } catch (e: Exception) {
            Result.failure(AppException.CommonException())
        }
    }
}
