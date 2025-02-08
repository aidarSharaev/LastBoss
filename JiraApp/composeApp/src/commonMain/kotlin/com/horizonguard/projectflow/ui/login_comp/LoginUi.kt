package com.horizonguard.projectflow.ui.login_comp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.horizonguard.projectflow.ui.login_comp.LoginComponent.LoginDestination
import com.horizonguard.projectflow.ui.login_comp.otp.OtpUi
import com.horizonguard.projectflow.ui.login_comp.signin.SignInUi
import com.horizonguard.projectflow.ui.login_comp.signup.SignUpUi

@Composable
internal fun LoginUi(
    component: LoginComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.loginStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is LoginDestination.SignIn -> SignInUi(component = child.component)
            is LoginDestination.SignUp -> SignUpUi(component = child.component)
            is LoginDestination.Otp -> OtpUi(component = child.component)
        }
    }
}