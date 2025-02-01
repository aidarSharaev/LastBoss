package com.horizonguard.jiraapp.di

import com.horizonguard.jiraapp.data.DATA_STORE_FILE_NAME
import com.horizonguard.jiraapp.data.createDataStore
import com.horizonguard.jiraapp.data.repository.PreferenceRepositoryImpl
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository
import org.koin.dsl.module

val desktopModule = module {

    single<PreferenceRepository> {
        PreferenceRepositoryImpl(createDataStore {
            DATA_STORE_FILE_NAME
        })
    }
}
