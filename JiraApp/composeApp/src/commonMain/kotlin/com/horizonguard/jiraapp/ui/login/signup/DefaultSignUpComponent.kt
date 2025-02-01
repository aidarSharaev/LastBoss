package com.horizonguard.jiraapp.ui.login.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

internal class DefaultSignUpComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit,
    private val navigateToOtp: () -> Unit,
): SignUpComponent {

    override val model: Value<SignUpUiState> = MutableValue(SignUpUiState())

    override fun onSendClick() {
        TODO("Not yet implemented")
    }

    override fun onSignInClick() {
        TODO("Not yet implemented")
    }

    class KoinFactory(

    ): SignUpComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            onBackClick: () -> Unit,
            navigateToOtp: () -> Unit
        ): SignUpComponent {
            return DefaultSignUpComponent(
                componentContext = componentContext,
                onBackClick = onBackClick,
                navigateToOtp = navigateToOtp,
            )
        }
    }
}