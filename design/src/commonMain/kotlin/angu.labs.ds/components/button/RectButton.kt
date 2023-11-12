package angu.labs.ds.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import angu.labs.ds.components.button.ButtonSize.Medium
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme

@Composable
internal fun ButtonShape.Rect.RectButton(
    text: String? = null,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    hierarchy: ButtonHierarchy = ButtonHierarchy.Primary,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    buttonSize: ButtonSize = Medium,
    state: ButtonState = ButtonState.Enabled
) {
    val (_, contentColor) = getStyleByHierarchyAndState(hierarchy, state)
    BaseButton(
        onClick = onClick,
        modifier = modifier,
        buttonShape = this@RectButton,
        hierarchy = hierarchy,
        state = state,
        buttonSize = buttonSize,
    ) {

        Box(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space2x),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Icon(
                        modifier = Modifier.size(buttonSize.iconSize()),
                        imageVector = leadingIcon,
                        tint = contentColor,
                        contentDescription = null
                    )
                }
                if (text != null) {
                    DsText(
                        text = text,
                        textAlign = TextAlign.Center,
                        contentColor = contentColor,
                        typography = buttonSize.typography()
                    )
                }
            }
            if (trailingIcon != null) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = DsTheme.spacers.space4x),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(buttonSize.iconSize()),
                        imageVector = trailingIcon,
                        tint = contentColor,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
