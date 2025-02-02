package com.horizonguard.jiraapp.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import jiraapp.composeapp.generated.resources.Res
import jiraapp.composeapp.generated.resources.rubik_bold
import jiraapp.composeapp.generated.resources.rubik_bolditalic
import jiraapp.composeapp.generated.resources.rubik_extrabold
import jiraapp.composeapp.generated.resources.rubik_extrabolditalic
import jiraapp.composeapp.generated.resources.rubik_italic
import jiraapp.composeapp.generated.resources.rubik_light
import jiraapp.composeapp.generated.resources.rubik_lightitalic
import jiraapp.composeapp.generated.resources.rubik_medium
import jiraapp.composeapp.generated.resources.rubik_mediumitalic
import jiraapp.composeapp.generated.resources.rubik_regular
import jiraapp.composeapp.generated.resources.rubik_semibold
import org.jetbrains.compose.resources.Font

val projectFlowGradient = arrayOf(
    0.0f to Color(0xffe2edf3),
    0.6f to Color(0xffedece8),
    1f to Color(0xfff6efda),
)

val robotoFamily: FontFamily
    @Composable
    get() = FontFamily(
        // light
        Font(Res.font.rubik_lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(Res.font.rubik_light, FontWeight.Light),

        // normal
        Font(Res.font.rubik_italic, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.rubik_regular, FontWeight.Normal),

        // medium
        Font(Res.font.rubik_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(Res.font.rubik_medium, FontWeight.Medium),

        // semi bold
        Font(Res.font.rubik_semibold, FontWeight.SemiBold),

        // bold
        Font(Res.font.rubik_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(Res.font.rubik_bold, FontWeight.Bold),

        // extra bold
        Font(Res.font.rubik_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(Res.font.rubik_extrabold, FontWeight.ExtraBold),
    )

val typo = Typography()

val projectFlowTypography: Typography
    @Composable
    get() = Typography().copy(
        displayLarge = typo.displayLarge,
        displayMedium = typo.displayMedium.copy(fontFamily = robotoFamily),
        displaySmall = typo.displaySmall.copy(fontFamily = robotoFamily),
        headlineLarge = typo.headlineLarge.copy(fontFamily = robotoFamily),
        headlineMedium = typo.headlineMedium.copy(fontFamily = robotoFamily),
        headlineSmall = typo.headlineSmall.copy(fontFamily = robotoFamily),
        titleLarge = typo.titleLarge.copy(fontFamily = robotoFamily),
        titleMedium = typo.titleMedium.copy(fontFamily = robotoFamily),
        titleSmall = typo.titleSmall.copy(fontFamily = robotoFamily),
        bodyLarge = typo.bodyLarge.copy(fontFamily = robotoFamily),
        bodyMedium = typo.bodyMedium.copy(fontFamily = robotoFamily),
        bodySmall = typo.bodySmall.copy(fontFamily = robotoFamily),
        labelLarge = typo.labelLarge.copy(fontFamily = robotoFamily),
        labelMedium = typo.labelMedium.copy(fontFamily = robotoFamily),
        labelSmall = typo.labelSmall.copy(fontFamily = robotoFamily),
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
        colorScheme = MaterialTheme.colorScheme,
        typography = projectFlowTypography,
        content = content
    )
}