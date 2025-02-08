package com.horizonguard.projectflow.ui.app_comp.space_comp

import com.arkivanov.decompose.ComponentContext

internal class DefaultSpaceComponent(
    componentContext: ComponentContext,
) : SpaceComponent {

    class KoinFactory(
    ) : SpaceComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
        ): SpaceComponent {
            return DefaultSpaceComponent(
                componentContext = componentContext,
            )
        }
    }
}