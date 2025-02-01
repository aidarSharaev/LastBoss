package com.horizonguard.jiraapp.di

import android.content.Context
import com.horizonguard.jiraapp.data.createDataStore
import com.horizonguard.jiraapp.data.repository.PreferenceRepositoryImpl
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository
import org.koin.dsl.module

val androidModule = module {

    single<PreferenceRepository> {
        PreferenceRepositoryImpl(createDataStore(get<Context>()))
    }
}