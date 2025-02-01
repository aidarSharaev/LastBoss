package com.horizonguard.jiraapp

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin

internal class JiraApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // inject Android context
            androidContext(this@MainApplication)
            // ...
        }
    }


}