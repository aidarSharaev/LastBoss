package com.horizonguard.jiraapp.utils

class TokenIsNull(override val message: String = "") : Throwable(message)

class Forbidden403(override val message: String = "") : Throwable(message)

class _404(override val message: String = "") : Throwable(message)

class HttpException(override val message: String = "") : Throwable(message)

class ConnectionException(override val message: String = "") : Throwable(message)
