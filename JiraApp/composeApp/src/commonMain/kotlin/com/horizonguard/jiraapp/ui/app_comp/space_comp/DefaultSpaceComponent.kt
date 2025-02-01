package com.horizonguard.jiraapp.ui.app_comp.space_comp

import com.arkivanov.decompose.ComponentContext
import com.horizonguard.jiraapp.ui.login_comp.signin.SignInComponent

internal class DefaultSpaceComponent(
    componentContext: ComponentContext,
) : SpaceComponent {

    class KoinFactory(
        private val spaceComponentFactory: SpaceComponent.KoinFactory,
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