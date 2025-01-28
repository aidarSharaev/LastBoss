package com.horizonguard.jiraapp.ui.login.signin

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

internal class DefaultSignInComponent(

) : SignInComponent {

    override val model: Value<SignInState> = MutableValue(SignInState())

    override fun onSignUpClick() {
        TODO("Not yet implemented")
    }

    override fun onSendClick() {
        TODO("Not yet implemented")
    }
}