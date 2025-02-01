package com.horizonguard.jiraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.defaultComponentContext
import com.horizonguard.jiraapp.ui.root.RootComponent
import com.horizonguard.jiraapp.ui.root.RootUi
import com.horizonguard.jiraapp.ui.root.localWindowSize
import org.koin.android.ext.android.inject

internal class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponentFactory: RootComponent.KoinFactory by inject()
        val rootComponent = rootComponentFactory(defaultComponentContext())

        setContent {
            CompositionLocalProvider(localWindowSize provides calculateWindowSizeClass(this)) {
                RootUi(component = rootComponent)
            }
        }
    }
}
