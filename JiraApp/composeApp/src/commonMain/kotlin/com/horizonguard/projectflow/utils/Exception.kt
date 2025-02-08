package com.horizonguard.projectflow.utils

internal sealed class AppException(
    override val message: String
) : Throwable(message) {

    internal class CommonException(override val message: String = "") : AppException(message)

    internal class TokenIsNull(override val message: String = "") : AppException(message)

    internal class EmailIsNull(override val message: String = "") : AppException(message)

    internal class Forbidden403(override val message: String = "") : AppException(message)

    internal class _404(override val message: String = "") : AppException(message)

    internal class HttpException(override val message: String = "") : AppException(message)

    internal class ConnectionException(override val message: String = "") : AppException(message)
}