package com.horizonguard.jiraapp.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.domain.repository.TokenRepository
import com.horizonguard.jiraapp.ui.app.AppComponent
import com.horizonguard.jiraapp.ui.login.LoginComponent
import com.horizonguard.jiraapp.ui.root.RootComponent.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

internal class DefaultRootComponent(
    componentContext: ComponentContext,
    private val tokenRepository: TokenRepository,
    private val appComponentFactory: AppComponent.KoinFactory,
    private val loginComponentFactory: LoginComponent.KoinFactory,
    private val dispatcher: CoroutineDispatcher,
) : RootComponent, ComponentContext by componentContext {

    override val model: Value<RootUiState> = MutableValue(RootUiState())

    private val navigation = StackNavigation<RootConfig>()
    private val scope = CoroutineScope(dispatcher + SupervisorJob())

    override val rootStack: Value<ChildStack<*, RootDestination>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.Login,
        handleBackButton = false,
        childFactory = ::rootChild,
    )

    init {
        scope.launch {
            tokenRepository.validateUser()
        }
    }

    private fun rootChild(
        config: RootConfig,
        componentContext: ComponentContext,
    ): RootDestination {
        return when (config) {
            is RootConfig.Login -> RootDestination.Login(
                loginComponentFactory(
                    componentContext = componentContext,
                    navigateToApp = navigation.replaceAll(RootDestination.App)
                )
            )

            is RootConfig.App -> RootDestination.App(
                appComponentFactory(
                    componentContext = componentContext,
                )
            )
        }
    }

    @Serializable
    private sealed interface RootConfig {

        @Serializable
        data object App : RootConfig

        @Serializable
        data object Login : RootConfig
    }

    class KoinFactory(
        private val appComponentFactory: AppComponent.KoinFactory,
        private val loginComponentFactory: LoginComponent.KoinFactory,
        private val tokenRepository: TokenRepository,
        private val dispatcher: CoroutineDispatcher,
    ) : RootComponent.KoinFactory {

        override fun invoke(componentContext: ComponentContext): RootComponent {
            return DefaultRootComponent(
                componentContext = componentContext,
                appComponentFactory = appComponentFactory,
                loginComponentFactory = loginComponentFactory,
                tokenRepository = tokenRepository,
                dispatcher = dispatcher,
            )
        }
    }
}
