package angu.labs.ds.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.card.CardMediaContent.CardMediaContentPosition
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.components.card.CardMediaContent
import angu.labs.ds.components.card.FixedTierContent

@Composable
public fun Card(
    modifier: Modifier = Modifier,
    fixedContent: FixedTierContent? = null,
    mediaContent: CardMediaContent? = null,
    buttonTier: CardButtonTier? = null,
    onCardClick: (() -> Unit)? = null
) {
    val shape = RoundedCornerShape(DsTheme.spacers.space3x)
    Column(
        modifier = modifier
            .clip(shape)
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = 2.dp, color = DsTheme.colors.borderOpaque),
                shape = shape
            ).then(if (onCardClick != null) Modifier.clickable { onCardClick() } else Modifier)
    ) {
        if (mediaContent != null && mediaContent.position == CardMediaContentPosition.Top) {
            CardMediaContent(mediaContent)
        } else {
            Spacer(modifier = Modifier.padding(top = DsTheme.spacers.space4x))
        }
        fixedContent?.let { FixedTierContent(it) }

        if (mediaContent != null && mediaContent.position == CardMediaContentPosition.Middle) {
            CardMediaContent(mediaContent)
        }
        buttonTier?.let { CardButtonTierContent(buttonTier) }

        if (mediaContent != null && mediaContent.position == CardMediaContentPosition.Bottom) {
            CardMediaContent(mediaContent)
        } else {
            Spacer(modifier = Modifier.padding(bottom = DsTheme.spacers.space4x))
        }
    }
}