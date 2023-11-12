package angu.labs.ds.components.artwork

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.color.DsColor
import angu.labs.ds.tokens.typography.DsTypography

@Composable
public fun Artwork(
    modifier: Modifier = Modifier,
    type: ArtworkType,
    artworkSize: ArtworkSize,
    shape: Shape = CircleShape,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .size(artworkSize.boxSize())
            .clip(shape)
            .background(type.color.background())
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (type) {
            is ArtworkType.Avatar -> {
                // todo to be done yet
            }

            is ArtworkType.Letter -> {
                val letter: String =
                    if (artworkSize == ArtworkSize.XSmall) type.text.firstOrNull()
                        .toString() else type.text

                DsText(
                    text = letter.uppercase(),
                    textAlign = TextAlign.Center,
                    contentColor = type.color.contentColor(),
                    typography = artworkSize.labelTypography(),
                    maxLines = 1,
                )
            }

            is ArtworkType.Icon -> {
                when {
                    type.imageVector != null -> Icon(
                        modifier = Modifier.background(Color.Transparent)
                            .size(artworkSize.iconSize),
                        imageVector = type.imageVector,
                        tint = type.color.contentColor(),
                        contentDescription = null,
                    )

                    type.painter != null -> Icon(
                        modifier = Modifier.background(Color.Transparent)
                            .size(artworkSize.iconSize),
                        painter = type.painter,
                        tint = type.color.contentColor(),
                        contentDescription = null,
                    )

                    else -> throw Exception("ImageVector and painter are null did you forget to setup an icon?")
                }
            }
        }
    }
}

public sealed class ArtworkType(public open val color: ArtworkDiversifyColor) {

    public data class Avatar(
        public val imgUrl: String,
        public override val color: ArtworkDiversifyColor
    ) : ArtworkType(color)

    public data class Letter(
        public val text: String,
        public override val color: ArtworkDiversifyColor
    ) : ArtworkType(color) {
        init {
            require(text.length <= 2) {
                "See https://base.uber.com/6d2425e9f/p/572f13-avatar/t/36a124"
            }
        }
    }

    public data class Icon(
        public val imageVector: ImageVector? = null,
        public val painter: Painter? = null,
        public override val color: ArtworkDiversifyColor
    ) : ArtworkType(color)
}

/**
 * See https://base.uber.com/6d2425e9f/p/572f13-avatar/t/14136a
 **/
public enum class ArtworkSize(
    public val boxSize: @Composable () -> Dp,
    public val iconSize: Dp,
    public val labelTypography: @Composable () -> DsTypography,
) {
    XSmall(
        boxSize = { DsTheme.spacers.space6x },
        iconSize = 12.dp,
        labelTypography = { DsTypography.Label.xSmall }
    ),
    Small(
        boxSize = { DsTheme.spacers.space9x },
        iconSize = 16.dp,
        labelTypography = { DsTypography.Label.medium }
    ),
    Medium(
        boxSize = { DsTheme.spacers.space12x },
        iconSize = 20.dp,
        labelTypography = { DsTypography.Heading.xSmall }
    ),
    Large(
        boxSize = { DsTheme.spacers.space16x },
        iconSize = 26.dp,
        labelTypography = { DsTypography.Heading.medium }
    )
}

/**
 * See https://base.uber.com/6d2425e9f/p/572f13-avatar/t/51db3a
 */
public enum class ArtworkDiversifyColor(
    public val background: @Composable () -> Color,
    public val contentColor: @Composable () -> Color
) {
    Default(
        background = { DsTheme.colors.backgroundPrimary },
        contentColor = { DsTheme.colors.contentPrimary }
    ),

    Blue(
        background = { DsColor.blue600 },
        contentColor = { DsTheme.colors.contentOnColor }
    ),

    Red(
        background = { DsColor.red600 },
        contentColor = { DsTheme.colors.contentOnColor }
    ),

    Yellow(
        background = { DsColor.yellow600 },
        contentColor = { DsTheme.colors.contentOnColorInverse }
    ),

    Purple(
        background = { DsColor.purple600 },
        contentColor = { DsTheme.colors.contentOnColor }
    ),

    Orange(
        background = { DsColor.orange600 },
        contentColor = { DsTheme.colors.contentOnColor }
    ),

    Green(
        background = { DsColor.green600 },
        contentColor = { DsTheme.colors.contentOnColor }
    ),

    Magenta(
        background = { DsColor.magenta600 },
        contentColor = { DsTheme.colors.contentOnColor }
    ),

    Gray(
        background = { DsColor.gray600 },
        contentColor = { DsTheme.colors.contentOnColor }
    )
}
