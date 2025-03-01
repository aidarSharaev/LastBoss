package com.horizonguard.projectflow.ui.login_comp.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.horizonguard.projectflow.ui.commom.LoginTextField
import com.horizonguard.projectflow.ui.root.localWindowSize
import com.horizonguard.projectflow.utils.projectFlowGradient
import com.horizonguard.projectflow.utils.projectFlowTypography
import projectflow.composeapp.generated.resources.Res
import projectflow.composeapp.generated.resources.app_name
import projectflow.composeapp.generated.resources.create_now
import projectflow.composeapp.generated.resources.email
import projectflow.composeapp.generated.resources.next
import projectflow.composeapp.generated.resources.no_account
import projectflow.composeapp.generated.resources.sign_in
import projectflow.composeapp.generated.resources.to_continue
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SignInUi(
    component: SignInComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.model.subscribeAsState()
    val isUiEnabled by component.isUiEnabled.subscribeAsState()

    SignInUiContent(
        email = state.email,
        isUiEnabled = isUiEnabled,
        onEmailChange = component::onEmailChange,
        onNextClick = component::onNextClick,
        onCreateClick = component::onCreateClick,
    )
}

@Composable
internal fun SignInUiContent(
    email: String,
    isUiEnabled:  Boolean,
    onEmailChange: (String) -> Unit,
    onCreateClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    val windowSizeClass = localWindowSize.current
    val padding = remember { windowSizeClass.loginSurfacePadding() }
    val letterSpacing = remember { windowSizeClass.loginLetterSpacing() }
    val spacerHeight = remember { windowSizeClass.loginSpacerHeight() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colorStops = projectFlowGradient)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.app_name),
            style = projectFlowTypography.displaySmall,
            letterSpacing = letterSpacing,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        Column(
            modifier = Modifier
                .shadow(elevation = 9.dp)
                .background(Color.White)
                .padding(padding),
        ) {
            Text(
                text = stringResource(Res.string.sign_in),
                style = projectFlowTypography.titleLarge,
                fontWeight = FontWeight.Medium,
            )

            Text(
                style = projectFlowTypography.bodyMedium,
                text = stringResource(Res.string.to_continue) + stringResource(Res.string.app_name)
            )

            Spacer(modifier = Modifier.height(spacerHeight))

            LoginTextField(
                value = email,
                onValueChange = onEmailChange,
                labelCondition = email.isEmpty(),
                isEnabled = isUiEnabled,
                labelResource = Res.string.email,
            )

            Row(
                modifier = Modifier
                    .padding(top = spacerHeight),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier,
                    style = projectFlowTypography.bodyMedium,
                    text = stringResource(Res.string.no_account)
                )

                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            enabled = true,
                            onClick = onCreateClick,
                        ),
                    text = stringResource(Res.string.create_now),
                    color = Color.Blue
                )
            }

            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = spacerHeight * 2),
                onClick = onNextClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    style = projectFlowTypography.labelLarge,
                    text = stringResource(Res.string.next),
                )
            }
        }
    }
}

internal fun WindowSizeClass.loginSurfacePadding(): Dp {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 26.dp
        else -> 45.dp
    }
}

internal fun WindowSizeClass.loginSpacerHeight(): Dp {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 10.dp
        else -> 16.dp
    }
}

internal fun WindowSizeClass.loginLetterSpacing(): TextUnit {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1.sp
        else -> 1.2.sp
    }
}
