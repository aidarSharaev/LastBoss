package com.horizonguard.projectflow.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

actual fun initKoin(appDeclaration: KoinAppDeclaration?): KoinApplication = startKoin {
    appDeclaration?.invoke(this)
    modules(desktopModule, appModule)
}

val koin = initKoin()
