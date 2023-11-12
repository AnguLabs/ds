package angu.labs.ds.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.theme.DsTheme

public data class CardMediaContent(
    val modifier: Modifier = Modifier,
    val size: MediaContentSize = MediaContentSize.Small,
    val layoutType: MediaContentLayout = MediaContentLayout.Bleed,
    val actionIconClick: (() -> Unit)? = null,
    val actionIcon: ImageVector? = null,
    val position: CardMediaContentPosition = CardMediaContentPosition.Middle,
    val content: @Composable () -> Unit,
) {
    public enum class CardMediaContentPosition {
        Top, Middle, Bottom
    }

    public enum class MediaContentLayout(public val padding: Dp = 0.dp) {
        Bleed, Inset(16.dp)
    }

    /**
     * See https://base.uber.com/6d2425e9f/p/02338d-card/b/284ec6
     */
    public class MediaContentSize private constructor(public val height: Dp) {
        public companion object {
            public val Small: MediaContentSize = MediaContentSize(146.dp)
            public val Large: MediaContentSize = MediaContentSize(216.dp)
            public fun Custom(size: Dp): MediaContentSize = MediaContentSize(size)
        }
    }
}

@Composable
internal fun CardMediaContent(
    content: CardMediaContent,
) {
    val space4x = DsTheme.spacers.space4x
    val (paddingTop, paddingBottom) = when (content.position) {
        CardMediaContent.CardMediaContentPosition.Top -> 0.dp to space4x
        CardMediaContent.CardMediaContentPosition.Middle -> space4x to space4x
        CardMediaContent.CardMediaContentPosition.Bottom -> space4x to 0.dp
    }
    Box(
        modifier = content.modifier
            .height(content.size.height)
            .fillMaxWidth()
            .padding(horizontal = content.layoutType.padding)
            .padding(top = paddingTop, bottom = paddingBottom)
    ) {
        DsButton(
            type = ButtonShape.Circle(icon = content.actionIcon),
            onClick = content.actionIconClick
        )
        content.content()
    }
}