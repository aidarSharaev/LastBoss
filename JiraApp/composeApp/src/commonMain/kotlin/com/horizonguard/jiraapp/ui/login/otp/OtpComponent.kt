package com.horizonguard.jiraapp.ui.login.otp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.ui.login.signin.SignInComponent
import com.horizonguard.jiraapp.ui.login.signin.SignInUiState

internal interface OtpComponent {

    val model: Value<OtpUiState>

    interface KoinFactory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): OtpComponent
    }
}