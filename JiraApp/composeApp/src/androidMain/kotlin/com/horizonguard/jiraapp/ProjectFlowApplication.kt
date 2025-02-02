package com.horizonguard.jiraapp

import android.app.Application
import com.horizonguard.jiraapp.di.androidModule
import com.horizonguard.jiraapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

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
