package com.horizonguard.projectflow.ui.root

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.horizonguard.projectflow.ui.app_comp.AppUi
import com.horizonguard.projectflow.ui.commom.ErrorScreen
import com.horizonguard.projectflow.ui.commom.LoadingScreen
import com.horizonguard.projectflow.ui.login_comp.LoginUi
import com.horizonguard.projectflow.ui.root.RootComponent.RootChild
import org.jetbrains.compose.ui.tooling.preview.Preview

expect val localWindowSize: ProvidableCompositionLocal<WindowSizeClass>

@Composable
@Preview
internal fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.model.subscribeAsState()

    when (val state = model.screenState) {
        is RootScreenState.Loading -> {
            LoadingScreen()
        }

        is RootScreenState.Error -> {
            ErrorScreen(
                onRepeat = component::callRequest,
            )
        }

        is RootScreenState.Success -> {
            Children(
                stack = component.rootStack,
                modifier = modifier,
                animation = stackAnimation(fade()),
            ) {
                when (val child = it.instance) {
                    is RootChild.Login -> LoginUi(component = child.component)
                    is RootChild.App -> AppUi(component = child.component)
                }
            }
        }
    }
}
