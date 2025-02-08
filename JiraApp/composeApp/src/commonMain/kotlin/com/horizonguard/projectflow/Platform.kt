package com.horizonguard.projectflow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform