package angu.labs.ds.extensions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.typography.DsTypography

@Composable
public fun ScreenState(
    modifier: Modifier = Modifier,
    title: String,
    type: ScreenType = ScreenType.Empty,
    description: String? = null,
    buttonText: String? = null,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        DsText(
            text = type.emoji,
            typography = DsTypography.Display.medium
        )

        DsText(
            modifier = Modifier.padding(top = DsTheme.spacers.space2x),
            text = title,
            typography = DsTypography.Heading.small
        )

        if (description != null) {
            DsText(
                modifier = Modifier.padding(top = DsTheme.spacers.space2x),
                text = description,
                typography = DsTypography.Paragraph.medium,
                textAlign = TextAlign.Center
            )
        }

        if (onClick != null && buttonText != null) {
            Spacer(modifier = Modifier.weight(1f))

            DsButton(
                text = buttonText,
                onClick = onClick,
                type = ButtonShape.Rect()
            )
        }
    }
}

public enum class ScreenType(public val emoji: String) {
    Error("\uD83D\uDCA9"),
    Empty("\uD83D\uDC40")
}