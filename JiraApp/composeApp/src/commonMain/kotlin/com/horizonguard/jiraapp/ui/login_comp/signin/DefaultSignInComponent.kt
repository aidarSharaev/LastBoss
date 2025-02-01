package com.horizonguard.jiraapp.ui.login_comp.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository

internal class DefaultSignInComponent(
    componentContext: ComponentContext,
    private val preferenceRepository: PreferenceRepository,
) : SignInComponent {

    override val model: Value<SignInUiState> = MutableValue(SignInUiState())

    override fun onSignUpClick() {
        TODO("Not yet implemented")
    }

    override fun onSendClick() {
        TODO("Not yet implemented")
    }

    class KoinFactory(
        private val preferenceRepository: PreferenceRepository,
    ) : SignInComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
        ): SignInComponent {
            return DefaultSignInComponent(
                componentContext = componentContext,
                preferenceRepository = preferenceRepository,
            )
        }
    }
}
