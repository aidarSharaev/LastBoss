package com.horizonguard.projectflow.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.horizonguard.projectflow.ui.app_comp.AppComponent
import com.horizonguard.projectflow.ui.login_comp.LoginComponent

internal interface RootComponent {

    val model: Value<RootUiState>

    val rootStack: Value<ChildStack<*, RootChild>>

    fun callRequest()

    sealed interface RootChild {
        class Login(val component: LoginComponent) : RootChild
        class App(val component: AppComponent) : RootChild
    }

    interface KoinFactory {

        operator fun invoke(
            componentContext: ComponentContext,
        ): RootComponent
    }
}
