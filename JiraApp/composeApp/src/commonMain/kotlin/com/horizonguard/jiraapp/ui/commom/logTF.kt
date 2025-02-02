package com.horizonguard.jiraapp.ui.commom

import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.horizonguard.jiraapp.utils.projectFlowTypography
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelCondition: Boolean,
    labelResource: StringResource,
) {
    val valueStyle = projectFlowTypography.bodyLarge.copy(lineHeight = 20.sp)
    val labelStyle = projectFlowTypography.labelSmall

    TextField(
        modifier = Modifier.imePadding(),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Blue,
            unfocusedLabelColor = Color.Black
        ),
        textStyle = valueStyle,
        leadingIcon = null,
        label = {
            Text(
                text = stringResource(labelResource),
                style = if (labelCondition) valueStyle else labelStyle,
                color = if (labelCondition) Color.LightGray else Color.Black,
            )
        }
    )
}
