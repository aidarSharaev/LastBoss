package com.horizonguard.projectflow.ui.login_comp.otp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface OtpComponent {

    val _model: Value<OtpUiState>

    fun navigateBack()
    fun otpChange(otp: String)
    fun onNextClick()

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit,
            navigateBack: () -> Unit,
        ): OtpComponent
    }
}