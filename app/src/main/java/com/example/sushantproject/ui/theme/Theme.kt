package com.example.sushantproject.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = EmergencyRedLight,
    onPrimary = EmergencyRedDark,
    secondary = LightGray,
    onSecondary = DarkGray,
    background = DarkGray,
    onBackground = White,
    surface = DarkGray,
    onSurface = White,
    error = EmergencyRedLight
)

private val LightColorScheme = lightColorScheme(
    primary = EmergencyRed,
    onPrimary = White,
    secondary = DarkGray,
    onSecondary = White,
    background = White,
    onBackground = DarkGray,
    surface = White,
    onSurface = DarkGray,
    error = EmergencyRed
)

@Composable
fun RaktaSevaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
