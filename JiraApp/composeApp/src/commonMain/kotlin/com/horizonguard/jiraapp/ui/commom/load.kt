package com.horizonguard.jiraapp.ui.commom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun load() {
    Column(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}