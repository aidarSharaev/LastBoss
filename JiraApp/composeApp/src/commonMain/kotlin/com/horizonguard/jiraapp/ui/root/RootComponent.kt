package com.horizonguard.jiraapp.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.ui.login.LoginComponent

internal interface RootComponent {

    val rootStack: Value<ChildStack<*, RootDestination>>

    sealed interface RootDestination {
        class Login(val component: LoginComponent) : RootDestination
        class App(val component: LoginComponent) : RootDestination
    }

    interface KoinFactory {
        operator fun invoke(
            componentContext: ComponentContext,
        ): RootComponent
    }
}