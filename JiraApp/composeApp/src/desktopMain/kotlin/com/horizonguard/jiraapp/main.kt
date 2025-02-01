package com.horizonguard.jiraapp

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.horizonguard.jiraapp.ui.login_comp.signin.SignInUiContentExpanded
import com.horizonguard.jiraapp.ui.root.localWindowSize
import jiraapp.composeapp.generated.resources.Res
import jiraapp.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() = application {
    CompositionLocalProvider(localWindowSize provides calculateWindowSizeClass()) {
        Window(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name),
        ) {
            // RootUi()
        }
    }
}

@Preview
@Composable
fun jj() {
    SignInUiContentExpanded()
}
