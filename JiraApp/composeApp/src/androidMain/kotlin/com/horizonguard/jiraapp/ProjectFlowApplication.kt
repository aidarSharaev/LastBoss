package com.horizonguard.jiraapp

import android.app.Application
import android.content.Context
import com.horizonguard.jiraapp.data.createDataStore
import com.horizonguard.jiraapp.data.repository.PreferenceRepositoryImpl
import com.horizonguard.jiraapp.di.androidModule
import com.horizonguard.jiraapp.di.appModule
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

val androidModule = module {

    single<PreferenceRepository> {
        PreferenceRepositoryImpl(createDataStore(get<Context>()))
    }
}

internal class ProjectFlowApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // inject Android context
            androidContext(this@ProjectFlowApplication)
            modules(androidModule, appModule)
        }
    }
}
