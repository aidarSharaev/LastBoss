package com.horizonguard.projectflow.di

import android.content.Context
import com.horizonguard.projectflow.data.createDataStore
import com.horizonguard.projectflow.data.repository.PreferenceRepositoryImpl
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

actual fun initKoin(appDeclaration: KoinAppDeclaration?) = startKoin {
    appDeclaration?.invoke(this)
}

val androidModule = module {

    single<PreferenceRepository> {
        PreferenceRepositoryImpl(createDataStore(get<Context>()))
    }
}

val koin = initKoin()