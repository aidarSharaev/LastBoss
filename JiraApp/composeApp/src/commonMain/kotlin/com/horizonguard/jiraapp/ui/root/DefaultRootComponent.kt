package com.horizonguard.jiraapp.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.horizonguard.jiraapp.domain.repository.PreferenceRepository
import com.horizonguard.jiraapp.ui.app_comp.AppComponent
import com.horizonguard.jiraapp.ui.login_comp.LoginComponent
import com.horizonguard.jiraapp.ui.root.RootComponent.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

internal class DefaultRootComponent(
    componentContext: ComponentContext,
    private val preferenceRepository: PreferenceRepository,
    private val appComponentFactory: AppComponent.KoinFactory,
    private val loginComponentFactory: LoginComponent.KoinFactory,
    private val dispatcher: CoroutineDispatcher,
) : RootComponent, ComponentContext by componentContext {

    override val model: Value<RootUiState> = MutableValue(RootUiState())

    private val navigation = StackNavigation<RootConfig>()
    private val scope = CoroutineScope(dispatcher + SupervisorJob())

    override val rootStack: Value<ChildStack<*, RootChild>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.Login,
        handleBackButton = false,
        childFactory = ::rootChild,
    )

    init {
        scope.launch {
            preferenceRepository.validateUser()
        }
    }

    private fun rootChild(
        config: RootConfig,
        componentContext: ComponentContext,
    ): RootChild {
        return when (config) {
            is RootConfig.Login -> RootChild.Login(
                loginComponentFactory(
                    componentContext = componentContext,
                    navigateToApp = {
                        navigation.replaceAll(RootConfig.App)
                    }
                )
            )

            is RootConfig.App -> RootChild.App(
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
        private val preferenceRepository: PreferenceRepository,
        private val dispatcher: CoroutineDispatcher,
    ) : RootComponent.KoinFactory {

        override fun invoke(componentContext: ComponentContext): RootComponent {
            return DefaultRootComponent(
                componentContext = componentContext,
                appComponentFactory = appComponentFactory,
                loginComponentFactory = loginComponentFactory,
                preferenceRepository = preferenceRepository,
                dispatcher = dispatcher,
            )
        }
    }
}
