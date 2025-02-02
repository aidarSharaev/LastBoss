package com.horizonguard.jiraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.defaultComponentContext
import com.horizonguard.jiraapp.di.koin
import com.horizonguard.jiraapp.ui.login_comp.signin.SignInUiContent
import com.horizonguard.jiraapp.ui.root.RootComponent
import com.horizonguard.jiraapp.ui.root.RootUi
import com.horizonguard.jiraapp.ui.root.localWindowSize
import com.horizonguard.jiraapp.utils.ProjectFlowTheme
import org.koin.android.ext.android.inject

internal class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponentFactory: RootComponent.KoinFactory by inject()
        val rootComponent = rootComponentFactory(defaultComponentContext())

        setContent {
            ProjectFlowTheme {
                CompositionLocalProvider(localWindowSize provides calculateWindowSizeClass(this)) {
                    RootUi(component = rootComponent)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewNotEmpty() {
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
private fun PreviewEmpty() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            SignInUiContent(
                "",
                {}, {}, {}
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun currentWindowAdaptiveInfo(): WindowSizeClass {
    val configuration = LocalConfiguration.current
    val size = DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
    return WindowSizeClass.calculateFromSize(size)
}
