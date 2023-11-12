package angu.labs.ds.tokens.typography

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.sp
import angu.labs.ds.theme.DsTheme

internal val DefaultTypography: Typography
    @Composable get() = Typography(
        h1 = DsTypography.Heading.xxLarge.style,
        h2 = DsTypography.Heading.xLarge.style,
        h3 = DsTypography.Heading.large.style,
        h4 = DsTypography.Heading.medium.style,
        h5 = DsTypography.Heading.small.style,
        h6 = DsTypography.Heading.xSmall.style,
        subtitle1 = DsTypography.Label.medium.style,
        subtitle2 = DsTypography.Label.small.style,
        body1 = DsTypography.Label.medium.style,
        body2 = DsTypography.Label.small.style,
        button = DsTypography.Label.small.style,
        caption = DsTypography.Label.xSmall.style,
        overline = DsTypography.Label.xSmall.style
    )

public class DsTypography private constructor(
    public val style: TextStyle
) {
    public sealed class Display {
        public companion object {
            private val default
                @Composable get() = TextStyle(fontFamily = DsTheme.font, fontWeight = FontWeight.W700)

            public val large: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 96.sp, lineHeight = 112.sp)
                )

            public val medium: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 52.sp, lineHeight = 64.sp)
                )

            public val small: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 44.sp, lineHeight = 52.sp)
                )

            public val xSmall: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 36.sp, lineHeight = 44.sp)
                )

            public val values: List<DsTypography>
                @Composable get() = listOf(xSmall, small, medium, large)
        }
    }

    public sealed class Heading {

        public companion object {
            private val default: TextStyle
                @Composable get() = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W700
                )
            public val xxLarge: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 40.sp, lineHeight = 52.sp)
                )

            public val xLarge: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 36.sp, lineHeight = 44.sp)
                )

            public val large: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 32.sp, lineHeight = 40.sp)
                )

            public val medium: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 28.sp, lineHeight = 36.sp)
                )

            public val small: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 24.sp, lineHeight = 32.sp)
                )

            public val xSmall: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 20.sp, lineHeight = 28.sp)
                )

            public val values: List<DsTypography>
                @Composable get() = listOf(
                    xSmall, small, medium, large, xLarge, xxLarge
                )
        }
    }

    public sealed class Label {
        public companion object {
            private val default: TextStyle
            @Composable get() = TextStyle(fontFamily = DsTheme.font, fontWeight = W500)

            public val xSmall: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 12.sp, lineHeight = 16.sp)
                )

            public val small: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 14.sp, lineHeight = 16.sp)
                )

            public val medium: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 16.sp, lineHeight = 20.sp)
                )

            public val large: DsTypography = DsTypography(
                TextStyle(fontSize = 18.sp, lineHeight = 24.sp)
            )

            public val values: List<DsTypography>
                @Composable get() = listOf(xSmall, small, medium, large)
        }
    }

    public sealed class Paragraph {
        public companion object {
            private val default: TextStyle
            @Composable get() = TextStyle(fontFamily = DsTheme.font, fontWeight = W400)

            public val xSmall: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 12.sp, lineHeight = 20.sp)
                )

            public val small: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 14.sp, lineHeight = 20.sp)
                )

            public val medium: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 16.sp, lineHeight = 24.sp)
                )

            public val large: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 18.sp, lineHeight = 28.sp)
                )
            public val values: List<DsTypography>
                @Composable get() = listOf(xSmall, small, medium, large)
        }
    }

    public sealed class Mono {
        public companion object {
            private val default: TextStyle
            @Composable get() = TextStyle(fontFamily = DsTheme.font, fontWeight = W500)

            public val xSmall: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 36.sp, lineHeight = 44.sp)
                )

            public val small: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 44.sp, lineHeight = 52.sp)
                )

            public val medium: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 52.sp, lineHeight = 64.sp)
                )

            public val large: DsTypography
                @Composable get() = DsTypography(
                    default.copy(fontSize = 96.sp, lineHeight = 112.sp)
                )
            public val values: List<DsTypography>
                @Composable get() = listOf(xSmall, small, medium, large)
        }
    }
}