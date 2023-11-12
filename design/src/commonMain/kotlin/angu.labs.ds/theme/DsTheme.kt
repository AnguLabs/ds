package angu.labs.ds.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import angu.labs.ds.tokens.DsIcons
import angu.labs.ds.tokens.color.ColorTokens
import angu.labs.ds.tokens.color.darkTokens
import angu.labs.ds.tokens.color.lightTokens
import angu.labs.ds.tokens.dimens.Spacers
import angu.labs.ds.tokens.shapes.DsShapes
import angu.labs.ds.tokens.typography.DefaultTypography

@Composable
public fun DsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    lightColors: ColorTokens = lightTokens,
    darkColors: ColorTokens = darkTokens,
    icons: DsIcons,
    fontFamily: FontFamily? = null,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors else lightColors

    ProvideDsColors(
        colors = colors,
        fontFamily = fontFamily ?: FontFamily.Default,
        icons = icons
    ) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = DefaultTypography,
            shapes = Shapes(
                small = DsShapes.small,
                medium = DsShapes.large,
                large = DsShapes.xLarge
            ),
            content = content
        )
    }
}

public object DsTheme {
    public val colors: ColorTokens
        @Composable
        get() = LocalDsColors.current

    public val shapes: DsShapes
        @Composable
        get() = LocalDsShapes.current

    public val spacers: Spacers
        @Composable
        get() = LocalDsSpacers.current

    public val font: FontFamily
        @Composable
        get() = LocalDsFonts.current

    public val icons: DsIcons
        @Composable
        get() = LocalDsIcons.current
}

@Composable
public fun ProvideDsColors(
    colors: ColorTokens,
    fontFamily: FontFamily,
    icons: DsIcons,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalDsColors provides colors,
        LocalDsShapes provides DsShapes,
        LocalDsSpacers provides Spacers,
        LocalDsFonts provides fontFamily,
        LocalDsIcons provides icons,
        content = content
    )
}

private fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)

private val LocalDsColors = staticCompositionLocalOf<ColorTokens> {
    error("No ColorsTokens provided")
}

private val LocalDsShapes = staticCompositionLocalOf<DsShapes> {
    error("No DsShapes provided")
}
private val LocalDsSpacers = staticCompositionLocalOf<Spacers> {
    error("No spacers provided")
}

private val LocalDsFonts = staticCompositionLocalOf<FontFamily> {
    error("No fonts provided")
}
private val LocalDsIcons = staticCompositionLocalOf<DsIcons> {
    error("No icons provided")
}