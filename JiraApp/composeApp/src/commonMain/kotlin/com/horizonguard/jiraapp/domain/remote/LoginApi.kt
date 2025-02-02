package com.horizonguard.jiraapp.domain.remote

import com.horizonguard.jiraapp.domain.model.Token
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

internal interface LoginApi {

    suspend fun validateToken(token: Token, email: String): Result<Token>
}

private const val URL = ""

internal class LoginApiImpl : LoginApi {

    val client = HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.HEADERS
        }
    }

    override suspend fun validateToken(token: Token, email: String): Result<Token> {
        client.post(URL) {
            contentType(ContentType.Application.Json)
            setBody(token)
        }
    }
}