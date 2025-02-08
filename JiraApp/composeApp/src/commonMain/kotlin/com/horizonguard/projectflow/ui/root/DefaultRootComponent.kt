package com.horizonguard.projectflow.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.horizonguard.projectflow.data.getExc
import com.horizonguard.projectflow.data.getValue
import com.horizonguard.projectflow.domain.iteractor.ValidateUseCase
import com.horizonguard.projectflow.ui.app_comp.AppComponent
import com.horizonguard.projectflow.ui.login_comp.LoginComponent
import com.horizonguard.projectflow.ui.root.RootComponent.RootChild
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

internal class DefaultRootComponent(
    componentContext: ComponentContext,
    private val validateUseCase: ValidateUseCase,
    private val appComponentFactory: AppComponent.KoinFactory,
    private val loginComponentFactory: LoginComponent.KoinFactory,
    private val dispatcher: CoroutineDispatcher,
) : RootComponent, ComponentContext by componentContext {

    private val _model: MutableValue<RootUiState> = MutableValue(RootUiState())
    override val model: Value<RootUiState> = _model

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
        callRequest()
    }

    override fun callRequest() {
        scope.launch {
            updateScreen(state = RootScreenState.Loading)
            val result = withContext(dispatcher) { validateUseCase.invoke() }
            result.getValue()?.let {
                updateScreen(state = RootScreenState.Success)
            } ?: run {
                updateScreen(state = RootScreenState.Error(result.getExc()))
            }
        }
    }

    private fun updateScreen(state: RootScreenState) {
        _model.update {
            it.copy(screenState = state)
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
        private val validateUseCase: ValidateUseCase,
        private val dispatcher: CoroutineDispatcher,
    ) : RootComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
        ): RootComponent {
            return DefaultRootComponent(
                componentContext = componentContext,
                appComponentFactory = appComponentFactory,
                loginComponentFactory = loginComponentFactory,
                validateUseCase = validateUseCase,
                dispatcher = dispatcher,
            )
        }
    }
}
