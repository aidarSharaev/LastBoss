package com.horizonguard.projectflow.ui.commom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.horizonguard.projectflow.utils.projectFlowGradient

@Composable
internal fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colorStops = projectFlowGradient))
    ) {
        CircularProgressIndicator(
            color = Color.Blue
        )
    }
}
