package com.horizonguard.projectflow.ui.login_comp.signup

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
import projectflow.composeapp.generated.resources.Res
import projectflow.composeapp.generated.resources.app_name
import projectflow.composeapp.generated.resources.create_acc_description
import projectflow.composeapp.generated.resources.create_acc_title
import projectflow.composeapp.generated.resources.email
import projectflow.composeapp.generated.resources.got_on_acc
import projectflow.composeapp.generated.resources.name
import projectflow.composeapp.generated.resources.next
import projectflow.composeapp.generated.resources.sign_in
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SignUpUi(
    component: SignUpComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.model.subscribeAsState()

    SignUpUiContent(
        email = state.email,
        name = state.name,
        onEmailChange = component::onEmailChange,
        onNameChange = component::onNameChange,
        onNextClick = component::onNextClick,
        onLoginClick = component::onLoginClick,
    )
}

@Composable
internal fun SignUpUiContent(
    email: String,
    name: String,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onLoginClick: () -> Unit,
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
                text = stringResource(Res.string.create_acc_title),
                style = projectFlowTypography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                style = projectFlowTypography.bodyMedium,
                text = stringResource(Res.string.create_acc_description),
            )

            Spacer(modifier = Modifier.height(spacerHeight))

            LoginTextField(
                value = email,
                onValueChange = onEmailChange,
                labelCondition = email.isEmpty(),
                labelResource = Res.string.email,
            )

            LoginTextField(
                value = name,
                onValueChange = onNameChange,
                labelCondition = name.isEmpty(),
                labelResource = Res.string.name,
            )

            Row(
                modifier = Modifier
                    .padding(top = spacerHeight),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier,
                    style = projectFlowTypography.bodyMedium,
                    text = stringResource(Res.string.got_on_acc)
                )

                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            enabled = true,
                            onClick = onLoginClick,
                        ),
                    text = stringResource(Res.string.sign_in),
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