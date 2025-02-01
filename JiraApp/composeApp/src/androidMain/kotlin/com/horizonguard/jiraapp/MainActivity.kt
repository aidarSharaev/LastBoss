package com.horizonguard.jiraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.horizonguard.jiraapp.data.createDataStore
import com.horizonguard.jiraapp.ui.root.RootUi

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val dataStore = remember {
                createDataStore(applicationContext)
            }

            RootUi()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    RootUi()
}