package com.horizonguard.projectflow.ui.login_comp.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update

internal class DefaultSignUpComponent(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit,
    private val navigateToOtp: (String) -> Unit,
): SignUpComponent, ComponentContext by componentContext {

    private val _model: MutableValue<SignUpUiState> = MutableValue(SignUpUiState())
    override val model: Value<SignUpUiState> = _model
    override val isUiEnabled: Value<Boolean> = MutableValue(true)

    override fun onNextClick() {
        TODO("Not yet implemented")
    }

    override fun onLoginClick() {
        TODO("Not yet implemented")
    }

    override fun onNameChange(name: String) {
        _model.update {
            it.copy(name = name)
        }
    }

    override fun onEmailChange(email: String) {
        _model.update {
            it.copy(email = email)
        }
    }

    class KoinFactory(

    ): SignUpComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            navigateBack: () -> Unit,
            navigateToOtp: (String) -> Unit
        ): SignUpComponent {
            return DefaultSignUpComponent(
                componentContext = componentContext,
                onBackClick = navigateBack,
                navigateToOtp = navigateToOtp,
            )
        }
    }
}