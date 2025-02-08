package com.horizonguard.projectflow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.horizonguard.projectflow.di.koin
import com.horizonguard.projectflow.ui.commom.ErrorScreen
import com.horizonguard.projectflow.ui.login_comp.signin.SignInUiContent
import com.horizonguard.projectflow.ui.login_comp.signup.SignUpUiContent
import com.horizonguard.projectflow.ui.root.RootComponent
import com.horizonguard.projectflow.ui.root.RootUi
import com.horizonguard.projectflow.ui.root.localWindowSize
import com.horizonguard.projectflow.utils.ProjectFlowTheme
import com.horizonguard.projectflow.utils.runOnUiThread
import org.jetbrains.compose.resources.stringResource
import projectflow.composeapp.generated.resources.Res
import projectflow.composeapp.generated.resources.app_name

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
private fun main() {

    val lifecycle = LifecycleRegistry()

    val rootComponentFactory: RootComponent.KoinFactory by koin.koin.inject()

    val rootComponent = runOnUiThread {
        rootComponentFactory(
            componentContext = DefaultComponentContext(lifecycle),
        )
    }

    application {
        val windowState = rememberWindowState()

        ProjectFlowTheme {

            Window(
                onCloseRequest = ::exitApplication,
                title = stringResource(Res.string.app_name),
            ) {
                LifecycleController(
                    lifecycleRegistry = lifecycle,
                    windowState = windowState,
                    windowInfo = LocalWindowInfo.current,
                )

                CompositionLocalProvider(localWindowSize provides calculateWindowSizeClass()) {
                    RootUi(component = rootComponent)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSignInNotEmpty() {
    currentWindowAdaptiveInfo {
        SignInUiContent(
            "aidaar",
            {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewSignInEmpty() {
    currentWindowAdaptiveInfo {
        SignInUiContent(
            "",
            {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewSignUpNotEmpty() {
    currentWindowAdaptiveInfo {
        SignUpUiContent(
            "aidaar",
            "aidara",
            {}, {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewSignUpEmpty() {
    currentWindowAdaptiveInfo {
        SignUpUiContent(
            "", "", {}, {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewErrorScreen() {
    currentWindowAdaptiveInfo {
        ErrorScreen { {} }
    }
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun currentWindowAdaptiveInfo(
    content: @Composable () -> Unit,
) {
    val size = DpSize(1000.dp, 1000.dp)
    val adaptive = WindowSizeClass.calculateFromSize(size)
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides adaptive) {
            content()
        }
    }
}