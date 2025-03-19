package com.horizonguard.projectflow.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.horizonguard.projectflow.data.getExc
import com.horizonguard.projectflow.data.getValue
import com.horizonguard.projectflow.domain.iteractor.ValidateUseCase
import com.horizonguard.projectflow.ui.app_comp.AppComponent
import com.horizonguard.projectflow.ui.login_comp.LoginComponent
import com.horizonguard.projectflow.ui.root.RootComponent.RootChild
import com.horizonguard.projectflow.utils.AppException
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import java.io.IOException
import kotlin.coroutines.CoroutineContext

internal class DefaultRootComponent(
    componentContext: ComponentContext,
    private val validateUseCase: ValidateUseCase,
    private val appComponentFactory: AppComponent.KoinFactory,
    private val loginComponentFactory: LoginComponent.KoinFactory,
    private val ioContext: CoroutineContext,
    mainContext: CoroutineContext,
) : RootComponent, ComponentContext by componentContext {

    private val _model: MutableValue<RootUiState> = MutableValue(RootUiState())
    override val model: Value<RootUiState> = _model

    private val navigation = StackNavigation<RootConfig>()
    private val scope = coroutineScope(mainContext + SupervisorJob())

    override val rootStack: Value<ChildStack<*, RootChild>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.App,
        handleBackButton = false,
        childFactory = ::rootChild,
    )

    init {
        println("aidar")
        callRequest()
    }

    override fun callRequest() {
        scope.launch {
            updateScreen(state = RootScreenState.Loading)
            val result = withContext(ioContext) { validateUseCase.invoke() }
            result.getValue()?.let { value ->
                if (value) {
                    navigation.replaceAll(RootConfig.App)
                }
                updateScreen(state = RootScreenState.Success)
            } ?: run {
                if (result.exceptionOrNull() is IOException || result.exceptionOrNull() is AppException.CommonException) {
                    updateScreen(state = RootScreenState.Error(result.getExc()))
                } else {
                    updateScreen(state = RootScreenState.Success)
                }
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
                        println("navigation.replaceAll(RootConfig.App)")
                        navigation.replaceAll(RootConfig.App)
                    },
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
        private val ioContext: CoroutineContext,
        private val mainContext: CoroutineContext,
    ) : RootComponent.KoinFactory {

        override fun invoke(
            componentContext: ComponentContext,
        ): RootComponent {
            return DefaultRootComponent(
                componentContext = componentContext,
                appComponentFactory = appComponentFactory,
                loginComponentFactory = loginComponentFactory,
                validateUseCase = validateUseCase,
                ioContext = ioContext,
                mainContext = mainContext,
            )
        }
    }
}
