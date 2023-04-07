package dev.mathewsmobile.picquest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Primary,
    primaryVariant = DarkPrimary,
    secondary = Accent,
    onPrimary = TextPrimary,
    onSecondary = TextSecondary,
    surface = AccentDark,
    onSurface = TextPrimary,
    background = Primary,
    onBackground = TextPrimary
)

private val LightColorPalette = lightColors(
    primary = DarkPrimary,
    onPrimary = TextPrimary,
    primaryVariant = Primary,
    secondary = Accent,
    onSecondary = TextPrimary,
    surface = Accent,
    onSurface = TextPrimary,
    background = Primary,
    onBackground = TextPrimary

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PicQuestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}