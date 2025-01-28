package com.horizonguard.jiraapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform