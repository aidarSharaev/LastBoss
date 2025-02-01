package com.horizonguard.jiraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import com.horizonguard.jiraapp.data.createDataStore
import com.horizonguard.jiraapp.ui.root.RootComponent
import com.horizonguard.jiraapp.ui.root.RootUi
import org.koin.android.ext.android.inject

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponentFactory: RootComponent.KoinFactory by inject()
        val rootComponent = rootComponentFactory(defaultComponentContext())

        setContent {
            val dataStore = remember {
                createDataStore(applicationContext)
            }

            RootUi(component = rootComponent)
        }
    }
}
