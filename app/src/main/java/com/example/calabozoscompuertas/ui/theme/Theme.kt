package com.example.calabozoscompuertas.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = tertiary,
    background = background,
    error = error
)

@Composable
fun CalabozosCompuertasTheme(content: @Composable () -> Unit) =
    MaterialTheme(colorScheme = DarkColorScheme, typography = Typography, content = content)