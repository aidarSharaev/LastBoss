package com.horizonguard.projectflow.ui.login_comp.otp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.horizonguard.projectflow.domain.repository.LoginRepository
import com.horizonguard.projectflow.domain.repository.PreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class DefaultOtpComponent(
    componentContext: ComponentContext,
    private val preferenceRepository: PreferenceRepository,
    private val loginRepository: LoginRepository,
    private val ioContext: CoroutineContext,
    private val mainContext: CoroutineContext,
    private val email: String,
    private val navigateToAppComponent: () -> Unit,
    private val navigateBack: () -> Unit,
) : OtpComponent, ComponentContext by componentContext {

    private val _model: MutableValue<OtpUiState> = MutableValue(OtpUiState())
    override val model: Value<OtpUiState> = _model

    override val isUiEnabled: MutableValue<Boolean> = MutableValue(true)

    private val scope = coroutineScope(mainContext + SupervisorJob())

    override fun onBackClick() {
        scope.launch {
            withContext(ioContext) { preferenceRepository.resetEmail() }
            navigateBack()
        }
    }

    override fun otpChange(otp: String) {
        if (otp.length < 5) {
            _model.update { it.copy(code = otp) }
        }
    }

    override fun onNextClick() {
        scope.launch {
            isUiEnabled.update { false }
            val code = _model.value.code
            val result = withContext(ioContext) {
                loginRepository.verifyOtp(email, code)
            }
            if (result.isSuccess) {
                isUiEnabled.update { true }
                navigateToAppComponent()
            } else {
                TODO(this.javaClass.name)
            }
        }
    }

    class KoinFactory(
        private val ioContext: CoroutineContext,
        private val mainContext: CoroutineContext,
        private val preferenceRepository: PreferenceRepository,
        private val loginRepository: LoginRepository,
    ) : OtpComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            email: String,
            navigateToApp: () -> Unit,
            navigateBack: () -> Unit,
        ): OtpComponent {
            return DefaultOtpComponent(
                componentContext = componentContext,
                navigateToAppComponent = navigateToApp,
                navigateBack = navigateBack,
                ioContext = ioContext,
                mainContext = mainContext,
                preferenceRepository = preferenceRepository,
                email = email,
                loginRepository = loginRepository,
            )
        }
    }
}