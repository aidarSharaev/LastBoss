package com.horizonguard.jiraapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.horizonguard.jiraapp.data.DATA_STORE_FILE_NAME
import com.horizonguard.jiraapp.data.createDataStore
import com.horizonguard.jiraapp.ui.root.RootUi

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "JiraApp",
    ) {
        RootUi()
    }
}