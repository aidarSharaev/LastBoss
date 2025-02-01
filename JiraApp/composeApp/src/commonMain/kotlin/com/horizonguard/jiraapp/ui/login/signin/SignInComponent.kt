package com.horizonguard.jiraapp.ui.login.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface SignInComponent {

    val model: Value<SignInUiState>

    fun onSignUpClick()
    fun onSendClick()

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit,
        ): SignInComponent
    }
}