package com.horizonguard.projectflow.ui.login_comp.otp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

internal class DefaultOtpComponent(
    component: ComponentContext,
    navigateToApp: () -> Unit,
    navigateBack: () -> Unit,
) : OtpComponent {

    override val _model: MutableValue<OtpUiState> = MutableValue(OtpUiState())
    val model: Value<OtpUiState> = _model

    override fun navigateBack() {
        navigateBack()
    }

    override fun otpChange(otp: String) {
        TODO("Not yet implemented")
    }

    override fun onNextClick() {
        TODO("Not yet implemented")
    }

    class KoinFactory() : OtpComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit,
            navigateBack: () -> Unit,
        ): OtpComponent {
            return DefaultOtpComponent(
                component = componentContext,
                navigateToApp = navigateToApp,
                navigateBack = navigateBack,
            )
        }
    }
}