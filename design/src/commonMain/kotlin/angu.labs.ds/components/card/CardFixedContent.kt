package angu.labs.ds.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.progress.ProgressStatic
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.typography.DsTypography


/**
 * Card fixed tier see https://base.uber.com/6d2425e9f/p/02338d-card/t/24a8bd
 */
public data class FixedTierContent(
    val eyeBrowIcon: ImageVector? = null,
    val eyeBrowText: String? = null,
    val hasCloseIcon: Boolean = false,
    val onClickClose: (() -> Unit)? = null,
    val artwork: ImageVector? = null,
    val headline: String? = null,
    val currency: String? = null,
    val progress: Double? = null,
    val paragraph: String? = null
)

@Composable
internal fun FixedTierContent(content: FixedTierContent) {
    val hasEyebrowContent = with(content) {
        eyeBrowIcon != null || eyeBrowText != null || hasCloseIcon
    }

    Column(modifier = Modifier.padding(horizontal = DsTheme.spacers.space4x)) {
        EyeBrowContent(modifier = Modifier.fillMaxWidth(), content = content)
        Row(
            modifier = Modifier.padding(
                top = if (hasEyebrowContent) DsTheme.spacers.space2x else 0.dp,
                bottom = DsTheme.spacers.space3x
            )
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(DsTheme.spacers.space2x)
            ) {
                if (content.headline != null) {
                    DsText(
                        text = content.headline,
                        typography = DsTypography.Heading.small,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }
                if (content.currency != null) {
                    DsText(
                        text = content.currency,
                        typography = DsTypography.Mono.medium,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }
                if (content.progress != null) {
                    ProgressStatic(
                        modifier = Modifier.fillMaxWidth(),
                        progress = content.progress
                    )
                }
                if (content.paragraph != null) {
                    DsText(
                        text = content.paragraph,
                        typography = DsTypography.Paragraph.medium
                    )
                }
            }
            if (content.artwork != null) {
                Icon(
                    modifier = Modifier
                        .padding(start = DsTheme.spacers.space4x)
                        .size(DsTheme.spacers.space14x),
                    imageVector = content.artwork,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun EyeBrowContent(modifier: Modifier, content: FixedTierContent) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (content.eyeBrowIcon != null) {
            Icon(
                imageVector = content.eyeBrowIcon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = DsTheme.spacers.space2x)
                    .size(DsTheme.spacers.space4x),
                tint = DsTheme.colors.contentPrimary
            )
        }
        if (content.eyeBrowText != null) {
            DsText(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = content.eyeBrowText,
                typography = DsTypography.Label.small,
                contentColor = DsTheme.colors.contentTertiary,
                maxLines = 1
            )
        }

        if (content.hasCloseIcon) {
            val clickable = content.onClickClose?.let { Modifier.clickable { it() } } ?: Modifier
            Icon(
                imageVector = DsTheme.icons.x,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = DsTheme.spacers.space2x)
                    .size(DsTheme.spacers.space6x)
                    .padding(DsTheme.spacers.space1x)
                    .clip(CircleShape)
                    .then(clickable),
                tint = DsTheme.colors.contentPrimary
            )
        }
    }
}
