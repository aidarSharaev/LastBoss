package com.horizonguard.projectflow.ui.login_comp.otp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface OtpComponent {

    val model: Value<OtpUiState>
    val isUiEnabled: Value<Boolean>

    fun onBackClick()
    fun otpChange(otp: String)
    fun onNextClick()

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            email: String,
            navigateToApp: () -> Unit,
            navigateBack: () -> Unit,
        ): OtpComponent
    }
}
