package com.horizonguard.jiraapp.ui.app

import com.arkivanov.decompose.ComponentContext

internal interface AppComponent {

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
        ): AppComponent
    }
}