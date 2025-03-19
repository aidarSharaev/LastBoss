package com.horizonguard.projectflow.ui.app_comp

import com.arkivanov.decompose.ComponentContext
import com.horizonguard.projectflow.ui.app_comp.space_comp.SpaceComponent

internal class DefaultAppComponent(
    componentContext: ComponentContext,
    private val spaceComponentFactory: SpaceComponent.KoinFactory,
) : AppComponent, ComponentContext by componentContext {

    init {
        println("123123")
    }

    class KoinFactory(
        private val spaceComponentFactory: SpaceComponent.KoinFactory,
    ) : AppComponent.KoinFactory {

        override operator fun invoke(
            componentContext: ComponentContext,
        ): AppComponent {
            return DefaultAppComponent(
                componentContext = componentContext,
                spaceComponentFactory = spaceComponentFactory,
            )
        }
    }
}
