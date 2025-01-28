package com.horizonguard.jiraapp.ui.login

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.ui.login.LoginComponent.*
import kotlinx.serialization.Serializable

internal class DefaultLoginComponent(

) : LoginComponent {

    private val navigation = StackNavigation<>()

    override val loginStack: Value<ChildStack<*, LoginDestination>> = childStack(
        source = navigation,
        serializer = LoginConfig.serializer(),
        initialConfiguration = LoginConfig.SignIn,
        handleBackButton = false,
        childFactory = ::rootChild,
    )

    @Serializable
    private sealed interface LoginConfig {

        @Serializable
        data object SignIn : LoginConfig

        @Serializable
        data object SignUp : LoginConfig

        @Serializable
        data object Otp : LoginConfig
    }
}