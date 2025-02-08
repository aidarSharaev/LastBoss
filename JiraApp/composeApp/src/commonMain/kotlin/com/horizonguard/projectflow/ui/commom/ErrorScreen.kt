package com.horizonguard.projectflow.ui.commom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.horizonguard.projectflow.ui.root.localWindowSize
import com.horizonguard.projectflow.utils.projectFlowGradient
import com.horizonguard.projectflow.utils.projectFlowTypography
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import projectflow.composeapp.generated.resources.Res
import projectflow.composeapp.generated.resources.check_connection
import projectflow.composeapp.generated.resources.error
import projectflow.composeapp.generated.resources.ic_cloud_fail
import projectflow.composeapp.generated.resources.repeat_request
import projectflow.composeapp.generated.resources.try_again_or_later
import projectflow.composeapp.generated.resources.try_err

@Composable
internal fun ErrorScreen(
    onRepeat: () -> Unit,
) {
    val windowSizeClass = localWindowSize.current
    val padding = windowSizeClass.getPadding()
    val bottomPadding = windowSizeClass.getBottomPadding()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colorStops = projectFlowGradient))
            .padding(horizontal = padding)
            .padding(top = padding)
            .padding(bottom = bottomPadding),
        verticalArrangement = windowSizeClass.getParentArrangement(),
        horizontalAlignment = windowSizeClass.getParentAlignment(),
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            verticalArrangement = windowSizeClass.getArrangement(),
            horizontalAlignment = windowSizeClass.getAlignment(),
        ) {
            Icon(
                painterResource(Res.drawable.ic_cloud_fail),
                contentDescription = "fail",
                modifier = Modifier.size(windowSizeClass.iconSize())
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(Res.string.error),
                style = windowSizeClass.textStyle(),
            )

            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(Res.string.try_err),
                    style = projectFlowTypography.titleLarge,
                )
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = stringResource(Res.string.check_connection),
                    style = projectFlowTypography.titleLarge,
                )
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = stringResource(Res.string.try_again_or_later),
                    style = projectFlowTypography.titleLarge,
                )
                Spacer(modifier = Modifier.height(36.dp))
            }
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                ),
                onClick = onRepeat
            ) {
                Text(
                    style = projectFlowTypography.labelLarge,
                    text = stringResource(Res.string.repeat_request),
                )
            }
        }
    }
}

private fun WindowSizeClass.getParentArrangement(): Arrangement.Vertical {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> Arrangement.Center
        else -> Arrangement.Top
    }
}

private fun WindowSizeClass.getParentAlignment(): Alignment.Horizontal {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> Alignment.CenterHorizontally
        else -> Alignment.Start
    }
}

private fun WindowSizeClass.getArrangement(): Arrangement.Vertical {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> Arrangement.spacedBy(
            space = 28.dp,
            alignment = Alignment.CenterVertically,
        )
        else -> Arrangement.Top
    }
}

private fun WindowSizeClass.getAlignment(): Alignment.Horizontal {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> Alignment.CenterHorizontally
        else -> Alignment.Start
    }
}

private fun WindowSizeClass.getPadding(): Dp {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> 32.dp
        else -> 64.dp
    }
}

private fun WindowSizeClass.getBottomPadding(): Dp {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> 108.dp
        else -> 64.dp
    }
}

private fun WindowSizeClass.iconSize(): Dp {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> 64.dp
        else -> 80.dp
    }
}

@Composable
private fun WindowSizeClass.textStyle(): TextStyle {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> projectFlowTypography.titleMedium
        else -> projectFlowTypography.headlineLarge
    }
}
