package com.horizonguard.jiraapp.ui.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.ui.login.LoginComponent.*
import com.horizonguard.jiraapp.ui.login.otp.OtpComponent
import com.horizonguard.jiraapp.ui.login.signin.SignInComponent
import com.horizonguard.jiraapp.ui.login.signup.SignUpComponent
import com.horizonguard.jiraapp.ui.root.RootComponent.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.Serializable

internal class DefaultLoginComponent(
    componentContext: ComponentContext,
    private val signInComponentFactory: SignInComponent.KoinFactory,
    private val signUpComponentFactory: SignUpComponent.KoinFactory,
    private val otpComponentFactory: OtpComponent.KoinFactory,
    private val navigateToApp: () -> Unit,
) : LoginComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<LoginConfig>()

    override val loginStack: Value<ChildStack<*, LoginDestination>> = childStack(
        source = navigation,
        serializer = LoginConfig.serializer(),
        initialConfiguration = LoginConfig.SignIn,
        handleBackButton = false,
        childFactory = ::loginChild,
    )

    private fun loginChild(
        config: LoginConfig,
        componentContext: ComponentContext,
    ): LoginDestination {
        return when (config) {
            is LoginConfig.SignIn -> LoginDestination.SignIn(
                signInComponentFactory.invoke(
                    componentContext = componentContext,
                    navigateToApp = { navigation.replaceAll(RootChild.App) }
                )
            )

            is LoginConfig.SignUp -> LoginDestination.SignUp(
                signUpComponentFactory.invoke(
                    componentContext = componentContext,
                    navigateToOtp =
                )
            )

            is LoginConfig.Otp -> LoginDestination.Otp(
                otpComponentFactory.invoke(
                    componentContext = componentContext,
                    navigateToApp = navigateToApp,
                )
            )
        }
    }

    @Serializable
    private sealed interface LoginConfig {

        @Serializable
        data object SignIn : LoginConfig

        @Serializable
        data object SignUp : LoginConfig

        @Serializable
        data object Otp : LoginConfig
    }

    class KoinFactory(
        private val signInComponentFactory: SignInComponent.KoinFactory,
        private val signUpComponentFactory: SignUpComponent.KoinFactory,
        private val otpComponentFactory: OtpComponent.KoinFactory,
        private val dispatcher: CoroutineDispatcher,
    ) : LoginComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit
        ): LoginComponent {
            return DefaultLoginComponent(
                componentContext = componentContext,
                signInComponentFactory = signInComponentFactory,
                signUpComponentFactory = signUpComponentFactory,
                otpComponentFactory = otpComponentFactory,
                navigateToApp = navigateToApp,
            )
        }
    }
}
