package com.horizonguard.jiraapp

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
import com.horizonguard.jiraapp.di.koin
import com.horizonguard.jiraapp.ui.login_comp.signin.SignInUiContent
import com.horizonguard.jiraapp.ui.login_comp.signup.SignUpUiContent
import com.horizonguard.jiraapp.ui.root.RootComponent
import com.horizonguard.jiraapp.ui.root.RootUi
import com.horizonguard.jiraapp.ui.root.localWindowSize
import com.horizonguard.jiraapp.utils.ProjectFlowTheme
import com.horizonguard.jiraapp.utils.runOnUiThread
import jiraapp.composeapp.generated.resources.Res
import jiraapp.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

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
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            SignInUiContent(
                "aidaar",
                {}, {}, {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSignInEmpty() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            SignInUiContent(
                "",
                {}, {}, {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSignUpNotEmpty() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            SignUpUiContent(
                "aidaar",
                "aidara",
                {}, {}, {}, {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSignUpEmpty() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            SignUpUiContent(
                "", "", {}, {}, {}, {}
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun currentWindowAdaptiveInfo(): WindowSizeClass {
    val size = DpSize(1000.dp, 1000.dp)
    return WindowSizeClass.calculateFromSize(size)
}