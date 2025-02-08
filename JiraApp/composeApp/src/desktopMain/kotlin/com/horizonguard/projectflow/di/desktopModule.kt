package com.horizonguard.projectflow.di

import com.horizonguard.projectflow.data.DATA_STORE_FILE_NAME
import com.horizonguard.projectflow.data.createDataStore
import com.horizonguard.projectflow.data.repository.PreferenceRepositoryImpl
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import org.koin.dsl.module

val desktopModule = module {

    single<PreferenceRepository> {
        PreferenceRepositoryImpl(createDataStore {
            DATA_STORE_FILE_NAME
        })
    }
}
