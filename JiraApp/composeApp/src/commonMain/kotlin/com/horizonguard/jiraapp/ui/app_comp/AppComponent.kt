package com.horizonguard.jiraapp.ui.app_comp

import com.arkivanov.decompose.ComponentContext

internal interface AppComponent {

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
        ): AppComponent
    }
}
