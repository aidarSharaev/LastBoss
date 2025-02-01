package com.horizonguard.jiraapp.ui.login_comp.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.horizonguard.jiraapp.ui.root.localWindowSize
import com.horizonguard.jiraapp.utils.projectFlowGradient
import com.horizonguard.jiraapp.utils.projectFlowTypography
import jiraapp.composeapp.generated.resources.Res
import jiraapp.composeapp.generated.resources.app_name
import jiraapp.composeapp.generated.resources.create_now
import jiraapp.composeapp.generated.resources.next
import jiraapp.composeapp.generated.resources.no_account
import jiraapp.composeapp.generated.resources.sign_in
import jiraapp.composeapp.generated.resources.to_continue
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SignInUi(
    component: SignInComponent,
    modifier: Modifier = Modifier,
) {
    val windowSizeClass = localWindowSize.current
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

        }

        WindowWidthSizeClass.Medium -> {

        }

        WindowWidthSizeClass.Expanded -> {
            SignInUiContentExpanded()
        }
    }
}

@Composable
fun SignInUiContentExpanded() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colorStops = projectFlowGradient)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.app_name),
            style = projectFlowTypography.displayLarge
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .shadow(elevation = 9.dp)
                .background(Color.White)
                .padding(45.dp),
        ) {
            Text(
                text = stringResource(Res.string.sign_in),
                style = projectFlowTypography.bodyMedium,
            )

            Text(
                style = projectFlowTypography.bodySmall,
                text = stringResource(Res.string.to_continue) + stringResource(Res.string.app_name)
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = ":",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedLabelColor = Color.Black
                )
            )

            Row(
                modifier = Modifier
                    .padding(top = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier,
                    style = projectFlowTypography.bodySmall,
                    text = stringResource(Res.string.no_account)
                )

                Text(
                    modifier = Modifier,
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontFamily = projectFlowTypography.bodySmall.fontFamily,
                                fontSize = projectFlowTypography.bodySmall.fontSize,
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline,
                            )
                        ) {
                            append(stringResource(Res.string.create_now))
                        }
                    },
                )
            }

            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 30.dp),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                )
            ) {
                Text(
                    modifier = Modifier,
                    style = projectFlowTypography.bodySmall,
                    text = stringResource(Res.string.next),
                    fontWeight = FontWeight.Light,
                )
            }
        }
    }
}
