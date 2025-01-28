package com.horizonguard.jiraapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "JiraApp",
    ) {
        App()
    }
}