package com.horizonguard.jiraapp.ui.commom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun error1() {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {}) {
            Text("error")
        }
    }
}

@Composable
@Preview
private fun Preview() {
    error1()
}
