package com.horizonguard.projectflow.ui.login_comp.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface SignUpComponent {

    val model: Value<SignUpUiState>

    fun onNextClick()
    fun onLoginClick()
    fun onNameChange(name: String)
    fun onEmailChange(email: String)

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            navigateBack: () -> Unit,
            navigateToOtp: () -> Unit,
        ): SignUpComponent
    }
}
