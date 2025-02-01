package com.horizonguard.jiraapp.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import jiraapp.composeapp.generated.resources.Res
import jiraapp.composeapp.generated.resources.roboto_bold
import jiraapp.composeapp.generated.resources.roboto_medium
import org.jetbrains.compose.resources.Font

val projectFlowGradient = arrayOf(
    0.0f to Color(0xffe2edf3),
    0.35f to Color(0xffedece8),
    1f to Color(0xfff6efda),
)

val robotoFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.roboto_bold, FontWeight.Bold),
        Font(Res.font.roboto_medium, FontWeight.Medium),
    )

val projectFlowTypography: Typography
    @Composable
    get() = Typography(
        displayLarge = TextStyle(
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
    )

@Composable
fun ProjectFlowTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {


    /*val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }*/

    MaterialTheme(
        //colors = colors,
        typography = projectFlowTypography,
        content = content
    )
}