package com.horizonguard.jiraapp.ui.login_comp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.ui.login_comp.otp.OtpComponent
import com.horizonguard.jiraapp.ui.login_comp.signin.SignInComponent
import com.horizonguard.jiraapp.ui.login_comp.signup.SignUpComponent

internal interface LoginComponent {

    val loginStack: Value<ChildStack<*, LoginDestination>>

    sealed interface LoginDestination {

        class SignIn(val component: SignInComponent) : LoginDestination

        class SignUp(val component: SignUpComponent) : LoginDestination

        class Otp(val component: OtpComponent) : LoginDestination
    }

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
            navigateToApp: () -> Unit,
        ): LoginComponent
    }
}
