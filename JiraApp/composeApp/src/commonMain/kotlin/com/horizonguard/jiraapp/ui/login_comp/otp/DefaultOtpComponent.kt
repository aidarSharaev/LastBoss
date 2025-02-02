package com.horizonguard.jiraapp.ui.login_comp.otp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal class DefaultOtpComponent(
    component: ComponentContext,
    navigateToApp: () -> Unit,
    navigateBack: () -> Unit,
) : OtpComponent {
    override val model: Value<OtpUiState>
        get() = TODO("Not yet implemented")

    class KoinFactory() : OtpComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit,
            navigateBack: () -> Unit,
        ): OtpComponent {
            return DefaultOtpComponent(
                component = componentContext,
                navigateToApp = navigateToApp,
                navigateBack = navigateBack,
            )
        }
    }
}