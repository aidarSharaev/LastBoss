package com.horizonguard.jiraapp.ui.login_comp.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository

internal interface SignInComponent {

    val model: Value<SignInUiState>

    fun onSignUpClick()
    fun onSendClick()

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            preferenceRepository: PreferenceRepository,
            navigateToApp: () -> Unit
        ): SignInComponent
    }
}