package com.horizonguard.jiraapp.ui.login_comp.otp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface OtpComponent {

    val model: Value<OtpUiState>

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit,
            navigateBack: () -> Unit,
        ): OtpComponent
    }
}