package angu.labs.ds.components.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.artwork.Artwork
import angu.labs.ds.components.artwork.ArtworkDiversifyColor
import angu.labs.ds.components.artwork.ArtworkSize
import angu.labs.ds.components.artwork.ArtworkType
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.button.ButtonHierarchy
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.components.text.DsText
import angu.labs.ds.getCurrentPlatform
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.dimens.Spacers
import angu.labs.ds.tokens.typography.DsTypography

@Composable
public fun NavBar(
    type: NavBarType = NavBarType.Fixed(isExpanded = false),
    startAction: NavbarAction = defaultNavigation { },
    endAction: NavbarAction? = null,
    title: String? = null,
    backgroundColor: Color = DsTheme.colors.backgroundPrimary,
) {
    val shapeIcons = if (type is NavBarType.Fixed) RectangleShape else CircleShape
    val isExpanded = if (type is NavBarType.Fixed) type.isExpanded else false
    val isFloating = type is NavBarType.Floating
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = Spacers.space2x)
    ) {

        BaseNavbar(
            startAction = {
                when (startAction) {
                    is NavbarAction.Icons -> ActionIcon(
                        shapeIcons = shapeIcons,
                        item = startAction.action1,
                        isFloating = isFloating
                    )

                    is NavbarAction.Button -> ActionButton(
                        isFloating = isFloating,
                        button = startAction,
                        backgroundColor = backgroundColor
                    )
                }
            },
            textContent = {
                if (!isFloating && !isExpanded) {
                    DsText(
                        modifier = Modifier.weight(1f),
                        text = if (isExpanded.not()) title.orEmpty() else "",
                        typography = DsTypography.Label.large,
                        contentColor = DsTheme.colors.contentPrimary,
                        maxLines = 1
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }

            },
            endAction = {
                if (endAction != null) {
                    when (endAction) {
                        is NavbarAction.Icons -> {
                            ActionIcon(
                                shapeIcons = shapeIcons,
                                item = endAction.action1,
                                isFloating = isFloating
                            )

                            if (endAction.action2 != null) {
                                ActionIcon(
                                    shapeIcons = shapeIcons,
                                    item = endAction.action2,
                                    isFloating = isFloating
                                )
                            }
                        }

                        is NavbarAction.Button -> ActionButton(
                            isFloating = isFloating,
                            button = endAction,
                            backgroundColor = backgroundColor
                        )
                    }
                }
            }
        )

        if (isExpanded && !isFloating) {
            DsText(
                modifier = Modifier
                    .padding(start = DsTheme.spacers.space2x),
                text = title.orEmpty(),
                typography = DsTypography.Display.xSmall,
                contentColor = DsTheme.colors.contentPrimary,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun ActionButton(
    isFloating: Boolean, backgroundColor: Color, button: NavbarAction.Button
) {
    Box(
        modifier = Modifier.then(
            if (isFloating) {
                Modifier.shadow(
                    2.dp,
                    DsTheme.shapes.circle,
                    spotColor = DsTheme.colors.contentOnColorInverse
                ).background(DsTheme.colors.backgroundPrimary)
            } else {
                Modifier.background(backgroundColor)
            }
        )
    ) {
        DsButton(
            text = button.text,
            onClick = button.onClick,
            hierarchy = ButtonHierarchy.Tertiary,
            type = ButtonShape.Pill()
        )
    }
}

@Composable
private fun ActionIcon(
    item: NavbarActionItem,
    shapeIcons: Shape,
    isFloating: Boolean
) {
    Artwork(
        modifier = Modifier
            .size(getCurrentPlatform().height)
            .then(
                if (isFloating) Modifier.shadow(
                    2.dp,
                    shapeIcons,
                    spotColor = DsTheme.colors.contentOnColorInverse
                ) else Modifier
            )
            .clickable { item.onClick() },
        artworkSize = ArtworkSize.Medium,
        shape = shapeIcons,
        type = ArtworkType.Icon(
            imageVector = item.icon(),
            color = ArtworkDiversifyColor.Default
        )
    )
}

public fun defaultNavigation(
    icon: (@Composable () -> ImageVector)? = null,
    onClick: (() -> Unit)
): NavbarAction {
    return NavbarAction.Icons(
        NavbarActionItem(
            icon = icon ?: { DsTheme.icons.arrowLeft },
            onClick = onClick
        )
    )
}

@Composable
private fun BaseNavbar(
    startAction: @Composable (() -> Unit)? = null,
    textContent: @Composable () -> Unit,
    endAction: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space2x)
    ) {
        startAction?.let { it() }
        textContent()
        endAction?.let { it() }
    }
}

public data class NavbarActionItem(
    val icon: @Composable () -> ImageVector,
    val onClick: () -> Unit,
)

public sealed class NavbarAction {
    public data class Icons(
        public val action1: NavbarActionItem,
        public val action2: NavbarActionItem? = null
    ) : NavbarAction()

    public data class Button(
        public val text: String,
        public val onClick: () -> Unit
    ) : NavbarAction()
}

public sealed class NavBarType {
    public object Floating : NavBarType()
    public data class Fixed(val isExpanded: Boolean) : NavBarType()
}