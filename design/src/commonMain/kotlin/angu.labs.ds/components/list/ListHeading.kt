package angu.labs.ds.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import angu.labs.ds.components.button.ButtonHierarchy.Secondary
import angu.labs.ds.components.button.ButtonState.Disabled
import angu.labs.ds.components.button.ButtonState.Enabled
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.components.button.ButtonSize
import angu.labs.ds.components.list.ListHeadingLabel.Circle
import angu.labs.ds.components.list.ListHeadingLabel.PillLabel
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.typography.DsTypography

@Composable
public fun ListHeading(
    heading: String,
    subHeading: String? = null,
    state: DsListState = DsListState.Visible,
    label: ListHeadingLabel? = null
) {
    val height = if (subHeading != null) DsTheme.spacers.space24x else DsTheme.spacers.space16x
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.background(DsTheme.colors.backgroundPrimary)
            .fillMaxWidth()
            .defaultMinSize(minHeight = height).padding(
                bottom = DsTheme.spacers.space2x,
                top = DsTheme.spacers.space6x,
                start = DsTheme.spacers.space4x,
                end = DsTheme.spacers.space4x
            )
    ) {
        val alignment = if (label is PillLabel || label is Circle) Top else Bottom
        Row(verticalAlignment = alignment) {
            Column {
                DsText(
                    text = heading,
                    textAlign = TextAlign.Left,
                    typography = DsTypography.Heading.small,
                    maxLines = 1
                )
                if (subHeading != null) {
                    DsText(
                        text = subHeading,
                        textAlign = TextAlign.Left,
                        typography = DsTypography.Paragraph.large,
                        maxLines = 1
                    )
                }
            }
            if (label != null) {
                Spacer(modifier = Modifier.weight(1f))
                when (label) {
                    is PillLabel -> {
                        DsButton(
                            text = label.text,
                            type = ButtonShape.Pill(),
                            hierarchy = Secondary,
                            buttonSize = ButtonSize.Small,
                            state = if (state == DsListState.Visible) Enabled else Disabled,
                            onClick = label.onClick
                        )
                    }

                    is ListHeadingLabel.Text -> {
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            DsText(
                                text = label.label,
                                typography = DsTypography.Label.large,
                                textAlign = TextAlign.End
                            )
                            if (label.paragraph != null) {
                                DsText(
                                    text = label.paragraph,
                                    textAlign = TextAlign.End,
                                    typography = DsTypography.Paragraph.medium
                                )
                            }
                        }
                    }

                    is Circle -> {
                        DsButton(
                            type = ButtonShape.Circle(label.icon),
                            hierarchy = Secondary,
                            buttonSize = ButtonSize.Small,
                            state = if (state == DsListState.Visible) Enabled else Disabled,
                            onClick = label.onClick
                        )
                    }
                }
            }
        }
    }
}

public enum class DsListState {
    Visible, Loading, Disabled
}

public sealed class ListHeadingLabel {
    public data class PillLabel(
        val text: String? = null,
        val onClick: (() -> Unit)? = null
    ) : ListHeadingLabel()

    public data class Text(
        val label: String,
        val paragraph: String? = null
    ) : ListHeadingLabel()

    public data class Circle(
        val icon: ImageVector? = null,
        val onClick: (() -> Unit)? = null,
    ) : ListHeadingLabel()
}