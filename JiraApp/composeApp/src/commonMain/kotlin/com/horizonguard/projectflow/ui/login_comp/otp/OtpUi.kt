package com.horizonguard.projectflow.ui.login_comp.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.horizonguard.projectflow.ui.commom.LoginTextField
import com.horizonguard.projectflow.ui.login_comp.signin.loginLetterSpacing
import com.horizonguard.projectflow.ui.login_comp.signin.loginSpacerHeight
import com.horizonguard.projectflow.ui.login_comp.signin.loginSurfacePadding
import com.horizonguard.projectflow.ui.root.localWindowSize
import com.horizonguard.projectflow.utils.projectFlowGradient
import com.horizonguard.projectflow.utils.projectFlowTypography
import org.jetbrains.compose.resources.stringResource
import projectflow.composeapp.generated.resources.Res
import projectflow.composeapp.generated.resources.app_name
import projectflow.composeapp.generated.resources.next
import projectflow.composeapp.generated.resources.otp
import projectflow.composeapp.generated.resources.otp_label
import projectflow.composeapp.generated.resources.resend

@Composable
internal fun OtpUi(
    component: OtpComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.model.subscribeAsState()
    val isUiEnabled by component.isUiEnabled.subscribeAsState()

    OtpUiContent(
        modifier = modifier,
        otp = state.code,
        isEnabled = isUiEnabled,
        onEmailChange = component::otpChange,
        onNextClick = component::onNextClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OtpUiContent(
    otp: String,
    isEnabled: Boolean,
    onEmailChange: (String) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val windowSizeClass = localWindowSize.current
    val padding = remember { windowSizeClass.loginSurfacePadding() }
    val letterSpacing = remember { windowSizeClass.loginLetterSpacing() }
    val spacerHeight = remember { windowSizeClass.loginSpacerHeight() }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Transparent),
                title = {},
                navigationIcon = {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                }
            )
        }
    ) {
        Column(
            modifier = modifier
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
                    text = stringResource(Res.string.otp),
                    style = projectFlowTypography.titleLarge,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(modifier = Modifier.height(spacerHeight))

                LoginTextField(
                    value = otp,
                    onValueChange = onEmailChange,
                    labelCondition = otp.isEmpty(),
                    isEnabled = isEnabled,
                    labelResource = Res.string.otp_label,
                )

                Row(
                    modifier = Modifier
                        .padding(top = spacerHeight),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier,
                        style = projectFlowTypography.bodyMedium,
                        text = stringResource(Res.string.resend)
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
}