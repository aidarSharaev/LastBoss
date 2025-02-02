package com.horizonguard.jiraapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

actual fun initKoin(appDeclaration: KoinAppDeclaration?) = startKoin() {
    appDeclaration?.invoke(this)

}

val koin = initKoin()