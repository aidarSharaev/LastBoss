package com.horizonguard.jiraapp.ui.login.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

internal class DefaultSignInComponent(
    componentContext: ComponentContext,
    private val navigateToApp: () -> Unit,
) : SignInComponent {

    override val model: Value<SignInUiState> = MutableValue(SignInUiState())

    override fun onSignUpClick() {
        TODO("Not yet implemented")
    }

    override fun onSendClick() {
        TODO("Not yet implemented")
    }

    class KoinFactory(
        private val navigateToApp: () -> Unit,
    ): SignInComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit,
        ): SignInComponent {
            return DefaultSignInComponent(
                componentContext = componentContext,
                navigateToApp = navigateToApp,
            )
        }
    }
}
