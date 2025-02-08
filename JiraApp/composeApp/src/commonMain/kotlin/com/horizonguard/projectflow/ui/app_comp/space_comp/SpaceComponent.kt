package com.horizonguard.projectflow.ui.app_comp.space_comp

import com.arkivanov.decompose.ComponentContext

internal interface SpaceComponent {

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
        ): SpaceComponent
    }
}
