package com.horizonguard.projectflow

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
import com.horizonguard.projectflow.ui.commom.ErrorScreen
import com.horizonguard.projectflow.ui.login_comp.otp.OtpUiContent
import com.horizonguard.projectflow.ui.login_comp.signin.SignInUiContent
import com.horizonguard.projectflow.ui.root.RootComponent
import com.horizonguard.projectflow.ui.root.RootUi
import com.horizonguard.projectflow.ui.root.localWindowSize
import com.horizonguard.projectflow.utils.ProjectFlowTheme
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

/*
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
*/

/*@Preview
@Composable
private fun PreviewErrorScreen() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            ErrorScreen({})
        }
    }

}*/
@Preview
@Composable
private fun PreviewOtp() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            OtpUiContent(
                "aidaar",
                {},
                {},
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
