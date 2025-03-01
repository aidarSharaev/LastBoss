package com.horizonguard.projectflow.ui.login_comp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.horizonguard.projectflow.ui.login_comp.LoginComponent.LoginDestination
import com.horizonguard.projectflow.ui.login_comp.otp.OtpComponent
import com.horizonguard.projectflow.ui.login_comp.signin.SignInComponent
import com.horizonguard.projectflow.ui.login_comp.signup.SignUpComponent
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

    @OptIn(DelicateDecomposeApi::class)
    private fun loginChild(
        config: LoginConfig,
        componentContext: ComponentContext,
    ): LoginDestination {
        return when (config) {
            is LoginConfig.SignIn -> LoginDestination.SignIn(
                signInComponentFactory.invoke(
                    componentContext = componentContext,
                    navigateToSignUp = { navigation.replaceAll(LoginConfig.SignUp) },
                    navigateToOtp = { email ->
                        navigation.push(LoginConfig.Otp(email))
                    },
                )
            )

            is LoginConfig.SignUp -> LoginDestination.SignUp(
                signUpComponentFactory.invoke(
                    componentContext = componentContext,
                    navigateBack = navigation::pop,
                    navigateToOtp = { email ->
                        navigation.push(LoginConfig.Otp(email))
                    },
                )
            )

            is LoginConfig.Otp -> LoginDestination.Otp(
                otpComponentFactory.invoke(
                    componentContext = componentContext,
                    navigateToApp = navigateToApp,
                    navigateBack = navigation::pop,
                    email = config.email,
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
        data class Otp(val email: String) : LoginConfig
    }

    class KoinFactory(
        private val signInComponentFactory: SignInComponent.KoinFactory,
        private val signUpComponentFactory: SignUpComponent.KoinFactory,
        private val otpComponentFactory: OtpComponent.KoinFactory,
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
