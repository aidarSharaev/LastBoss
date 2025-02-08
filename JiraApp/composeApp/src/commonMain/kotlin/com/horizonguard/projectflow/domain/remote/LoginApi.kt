package com.horizonguard.projectflow.domain.remote

import com.horizonguard.projectflow.data.model.AuthBody
import com.horizonguard.projectflow.utils.AppException
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging

internal interface LoginApi {

    suspend fun validateToken(body: AuthBody): Result<AuthBody>
}

private const val URL = ""

internal class LoginApiImpl : LoginApi {

    private val client = HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.HEADERS
        }
    }

    override suspend fun validateToken(body: AuthBody): Result<AuthBody> {
        /*client.post(URL) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }*/
        return Result.failure(AppException.Forbidden403())
    }
}