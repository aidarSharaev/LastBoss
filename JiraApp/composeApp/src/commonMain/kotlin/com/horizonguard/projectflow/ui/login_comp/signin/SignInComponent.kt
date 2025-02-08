package com.horizonguard.projectflow.ui.login_comp.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface SignInComponent {

    val model: Value<SignInUiState>

    fun onCreateClick()
    fun onNextClick()
    fun onEmailChange(email: String)

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
        ): SignInComponent
    }
}