package com.horizonguard.jiraapp.ui.login_comp.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface SignUpComponent {

    val model: Value<SignUpUiState>

    fun onSendClick()

    fun onSignInClick()

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            navigateBack: () -> Unit,
            navigateToOtp: () -> Unit,
        ): SignUpComponent
    }
}
