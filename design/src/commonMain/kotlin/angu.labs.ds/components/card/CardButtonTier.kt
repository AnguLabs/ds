package angu.labs.ds.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import angu.labs.ds.components.button.ButtonHierarchy
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.button.ButtonShape.Pill
import angu.labs.ds.components.button.ButtonShape.Rect
import angu.labs.ds.components.button.ButtonSize
import angu.labs.ds.components.card.CardButtonTier.Alignment.Center
import angu.labs.ds.components.card.CardButtonTier.Alignment.CenterFullWidth
import angu.labs.ds.components.card.CardButtonTier.Alignment.Left
import angu.labs.ds.components.card.CardButtonTier.Alignment.Right
import angu.labs.ds.theme.DsTheme

/**
 * See https://base.uber.com/6d2425e9f/p/02338d-card/t/62043f
 */
public data class CardButtonTier(
    val text: String,
    val onClick: () -> Unit,
    val buttonTierAlignment: Alignment = Center,
) {
    public enum class Alignment {
        Center, Left, Right, CenterFullWidth
    }
}

@Composable
internal fun CardButtonTierContent(content: CardButtonTier) {
    val (text, onClick, buttonTierAlignment) = content
    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = DsTheme.spacers.space4x)) {
        val modifier = when (buttonTierAlignment) {
            Center -> Modifier.align(Alignment.Center)
            Left -> Modifier.align(Alignment.CenterStart)
            Right -> Modifier.align(Alignment.CenterEnd)
            CenterFullWidth -> Modifier.align(Alignment.Center)
        }
        val shape = if (buttonTierAlignment != CenterFullWidth) Pill() else Rect()
        DsButton(
            modifier = modifier,
            text = text,
            type = shape,
            onClick = onClick,
            hierarchy = ButtonHierarchy.Secondary,
            buttonSize = ButtonSize.Small
        )
    }
}