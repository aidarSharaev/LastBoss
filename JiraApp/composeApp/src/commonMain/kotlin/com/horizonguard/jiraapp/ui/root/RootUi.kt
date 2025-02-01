package com.horizonguard.jiraapp.ui.root

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.horizonguard.jiraapp.ui.app.AppUi
import com.horizonguard.jiraapp.ui.commom.error1
import com.horizonguard.jiraapp.ui.commom.load
import com.horizonguard.jiraapp.ui.login.LoginUi
import com.horizonguard.jiraapp.ui.root.RootComponent.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
internal fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.model.subscribeAsState()

    when (model.screenState) {
        is RootScreenState.Loading -> {
            load()
        }

        is RootScreenState.Error -> {
            error1()
        }

        is RootScreenState.Success -> {
            Children(
                stack = component.rootStack,
                modifier = modifier,
                animation = stackAnimation(fade())
            ) {
                when (val child = it.instance) {
                    is RootChild.Login -> LoginUi(component = child.component)
                    is RootChild.App -> AppUi(component = child.component)
                }
            }
        }
    }
}
