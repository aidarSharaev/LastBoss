package com.horizonguard.jiraapp.ui.root

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.compositionLocalOf

actual val localWindowSize =
    compositionLocalOf<WindowSizeClass> { error("windowSizeError") }
