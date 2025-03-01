package com.horizonguard.projectflow

import android.app.Application
import android.content.Context
import com.horizonguard.projectflow.data.createDataStore
import com.horizonguard.projectflow.data.repository.PreferenceRepositoryImpl
import com.horizonguard.projectflow.di.androidModule
import com.horizonguard.projectflow.di.appModule
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

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
