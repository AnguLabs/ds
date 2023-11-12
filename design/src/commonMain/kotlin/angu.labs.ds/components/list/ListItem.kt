package angu.labs.ds.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.artwork.Artwork
import angu.labs.ds.components.artwork.ArtworkSize
import angu.labs.ds.components.artwork.ArtworkType
import angu.labs.ds.components.badge.Badge
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.components.button.ButtonSize
import angu.labs.ds.components.button.ButtonState
import angu.labs.ds.components.control.Switch
import angu.labs.ds.components.control.SwitchBehavior
import angu.labs.ds.components.control.SwitchState
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.dimens.Spacers
import angu.labs.ds.tokens.dimens.Spacers.space1x
import angu.labs.ds.tokens.dimens.Spacers.space4x
import angu.labs.ds.tokens.typography.DsTypography

@Composable
public fun ListItem(
    onClick: (() -> Unit)? = null,
    type: ListItemType = ListItemType.Standard,
    startContent: StartContentListItem? = null,
    textContent: ListTextContent? = null,
    endContent: EndContentListItem? = null,
    showDivider: Boolean = true,
) {
    var itemPadding by remember { mutableStateOf(if (startContent == null) type.padding else 0.dp) }
    val interaction = remember { MutableInteractionSource() }
    val density = LocalDensity.current
    val startContentSize = startContent?.artworkSize?.boxSize?.let { boxSize ->
        if (boxSize() > defaultMinBoxSize) boxSize() else defaultMinBoxSize
    } ?: 0.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (type == ListItemType.Standard) Modifier.defaultMinSize(minHeight = minHeightStandardItem) else Modifier
            ).then(
                if (onClick != null) Modifier.clickable(
                    interactionSource = interaction,
                    indication = rememberRipple(),
                    onClick = onClick,
                    onClickLabel = null
                ) else Modifier
            )
    ) {
        if (startContent != null) {
            Column(
                modifier = Modifier
                    .defaultMinSize(DsTheme.spacers.space16x)
                    .align(Alignment.CenterStart)
                    .matchParentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Artwork(
                    type = startContent.type,
                    artworkSize = startContent.artworkSize,
                    shape = startContent.shape,
                    onClick = startContent.onClick
                )
            }
        }
        if (textContent != null) {
            val paddingStart =
                if (startContent != null) startContentSize else DsTheme.spacers.space4x
            Column(
                modifier = Modifier
                    .padding(
                        start = paddingStart,
                        end = endContent?.width ?: DsTheme.spacers.space4x
                    )
                    .align(Alignment.CenterStart)
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        val height = with(density) { coordinates.size.height.toDp() }
                        if (startContent != null && height > startContentSize) {
                            itemPadding = type.padding
                        }
                    }
            ) {
                Row(modifier = Modifier.padding(top = itemPadding)) {
                    TextItem(
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = if (textContent.supportParagraph == null) itemPadding else 0.dp),
                        text = textContent.startText,
                        textMaxLine = textContent.startTextMaxLines,
                        textAlign = TextAlign.Start,
                        paragraph = textContent.startParagraph,
                        paragraphMaxLine = textContent.supportParagraphMaxLines,
                        spaceBetweenLines = type.spaceBetweenLines
                    )
                    if (textContent.endParagraph != null || textContent.endText != null) {
                        TextItem(
                            modifier = Modifier.weight(1f),
                            text = textContent.endText,
                            textMaxLine = 1,
                            textAlign = TextAlign.End,
                            paragraph = textContent.endParagraph,
                            paragraphMaxLine = 1,
                            spaceBetweenLines = type.spaceBetweenLines
                        )
                    }
                }
                if (textContent.supportParagraph != null) {
                    DsText(
                        text = textContent.supportParagraph.capitalize(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = type.spaceBetweenLines, bottom = itemPadding),
                        typography = DsTypography.Paragraph.small,
                        textAlign = TextAlign.Start,
                        contentColor = DsTheme.colors.contentSecondary,
                        maxLines = textContent.supportParagraphMaxLines,
                    )
                }
            }
        }
        if (endContent != null) {
            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                ControlAreaComponent(endContent, onClick = onClick)
            }
        }

        if (showDivider) {
            val defaultPadding =
                if (startContent != null) startContentSize else DsTheme.spacers.space4x
            Divider(
                modifier = Modifier
                    .padding(start = defaultPadding)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                color = DsTheme.colors.borderOpaque
            )
        }
    }
}

public enum class ListItemType(
    public val spaceBetweenLines: Dp = 0.dp,
    public val padding: Dp
) {
    Compact(padding = Spacers.space3x),
    Standard(spaceBetweenLines = space1x, padding = space4x)
}

public sealed class EndContentListItem(public val width: Dp) {
    public enum class ChevronDirection(public val icon: @Composable () -> ImageVector) {
        Up({ DsTheme.icons.chevronUp }),
        Down({ DsTheme.icons.chevronDown }),
        Left({ DsTheme.icons.chevronLeft }),
        Right({ DsTheme.icons.chevronRight })
    }

    public data class ActionItem(
        val icon: ImageVector,
        val onClick: () -> Unit,
        val state: ButtonState = ButtonState.Enabled
    )

    public data class Badge(val color: Color? = null) : EndContentListItem(controlAreaWidth)
    public data class Chevron(
        val direction: ChevronDirection = ChevronDirection.Right
    ) : EndContentListItem(controlAreaWidth)

    public data class Switch(
        val behavior: SwitchBehavior = SwitchBehavior.Off,
        val state: SwitchState = SwitchState.Enabled,
    ) : EndContentListItem(controlAreaWidth)

    public data class Action(
        val action1: ActionItem,
        val action2: ActionItem,
    ) : EndContentListItem(controlAreaDoubleAction)

    public data class ActionText(
        val label: String,
        val onClick: (() -> Unit)? = null,
        val buttonState: ButtonState = ButtonState.Enabled
    ) : EndContentListItem(controlAreaActionText)
}

@Composable
private fun TextItem(
    modifier: Modifier = Modifier,
    text: String? = null,
    textMaxLine: Int? = null,
    paragraph: String? = null,
    paragraphMaxLine: Int? = null,
    textAlign: TextAlign,
    spaceBetweenLines: Dp
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        DsText(
            modifier = Modifier.fillMaxWidth(),
            text = text.orEmpty().capitalize(),
            typography = DsTypography.Label.medium,
            textAlign = textAlign,
            contentColor = DsTheme.colors.contentPrimary,
            maxLines = textMaxLine ?: Int.MAX_VALUE,
        )
        if (paragraph != null) {
            DsText(
                text = paragraph.capitalize(),
                modifier = Modifier.fillMaxWidth().padding(top = spaceBetweenLines),
                typography = DsTypography.Paragraph.small,
                textAlign = textAlign,
                contentColor = DsTheme.colors.contentSecondary,
                maxLines = paragraphMaxLine ?: Int.MAX_VALUE,
            )
        }
    }
}

public data class StartContentListItem(
    public val type: ArtworkType,
    public val artworkSize: ArtworkSize,
    public val shape: Shape = CircleShape,
    public val onClick: (() -> Unit)? = null
)

public data class ListTextContent(
    public val startText: String? = null,
    public val startTextMaxLines: Int = 1,
    public val startParagraph: String? = null,
    public val startParagraphMaxLines: Int = 1,
    public val endText: String? = null,
    public val endParagraph: String? = null,
    public val supportParagraph: String? = null,
    public val supportParagraphMaxLines: Int = 1,
)

@Composable
private fun ControlAreaComponent(
    endContent: EndContentListItem,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.width(endContent.width).fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (endContent) {
            is EndContentListItem.Badge -> Badge(endContent.color)
            is EndContentListItem.Chevron -> Icon(
                modifier = Modifier.size(DsTheme.spacers.space6x),
                imageVector = endContent.direction.icon(),
                tint = DsTheme.colors.contentStateDisabled,
                contentDescription = null
            )

            is EndContentListItem.ActionText -> DsButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = endContent.label,
                onClick = endContent.onClick,
                type = ButtonShape.Pill(),
                state = endContent.buttonState,
                buttonSize = ButtonSize.Small
            )

            is EndContentListItem.Action -> Row(
                modifier = Modifier.fillMaxSize().padding(end = DsTheme.spacers.space2x),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DsButton(
                    onClick = endContent.action1.onClick,
                    type = ButtonShape.Circle(endContent.action1.icon),
                    state = endContent.action1.state,
                    buttonSize = ButtonSize.Small
                )

                DsButton(
                    onClick = endContent.action2.onClick,
                    type = ButtonShape.Circle(endContent.action2.icon),
                    state = endContent.action2.state,
                    buttonSize = ButtonSize.Small
                )
            }

            is EndContentListItem.Switch -> Column(
                modifier = Modifier.padding(end = DsTheme.spacers.space2x)
            ) {
                Switch(
                    behavior = endContent.behavior,
                    state = endContent.state,
                    onCheckedChange = {
                        onClick?.invoke()
                    }
                )
            }
        }
    }
}

private val controlAreaWidth: Dp = 64.dp
private val minHeightStandardItem: Dp = 64.dp
private val controlAreaActionText: Dp = 100.dp
private val controlAreaDoubleAction: Dp = 100.dp
private val defaultMinBoxSize = 64.dp
