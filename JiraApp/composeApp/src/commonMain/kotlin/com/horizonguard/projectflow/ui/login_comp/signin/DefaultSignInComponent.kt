package com.horizonguard.projectflow.ui.login_comp.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.horizonguard.projectflow.data.ifSuccess
import com.horizonguard.projectflow.domain.repository.LoginRepository
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class DefaultSignInComponent(
    componentContext: ComponentContext,
    private val loginRepository: LoginRepository,
    private val ioContext: CoroutineContext,
    val mainContext: CoroutineContext,
    private val navigateToSignUp: () -> Unit,
    private val navigateToOtp: (String) -> Unit,
) : SignInComponent, ComponentContext by componentContext {

    private val _model: MutableValue<SignInUiState> = MutableValue(SignInUiState())
    override val model: Value<SignInUiState> = _model

    private val scope = coroutineScope(mainContext + SupervisorJob())
    override var isUiEnabled: MutableValue<Boolean> = MutableValue(true)

    override fun onCreateClick() {
        navigateToSignUp()
    }

    override fun onNextClick() {
        scope.launch {
            isUiEnabled.update { false }
            val email = _model.value.email
            val result = withContext(ioContext) {
                loginRepository.loginByEmail(email)
            }
            result.ifSuccess { value ->
                isUiEnabled.update { true }
                navigateToOtp(value)
            }
        }
    }

    override fun onEmailChange(email: String) {
        _model.update {
            it.copy(email = email)
        }
    }

    class KoinFactory(
        private val loginRepository: LoginRepository,
        private val ioContext: CoroutineContext,
        private val mainContext: CoroutineContext,
    ) : SignInComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            navigateToOtp: (email: String) -> Unit,
            navigateToSignUp: () -> Unit,
        ): SignInComponent {
            return DefaultSignInComponent(
                componentContext = componentContext,
                loginRepository = loginRepository,
                ioContext = ioContext,
                navigateToOtp = navigateToOtp,
                navigateToSignUp = navigateToSignUp,
                mainContext = mainContext,
            )
        }
    }
}
