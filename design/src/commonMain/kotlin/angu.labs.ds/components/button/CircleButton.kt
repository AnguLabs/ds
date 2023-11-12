package angu.labs.ds.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme

@Composable
internal fun CommonButton(
    text: String? = null,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    hierarchy: ButtonHierarchy = ButtonHierarchy.Secondary,
    buttonSize: ButtonSize = ButtonSize.Medium,
    state: ButtonState = ButtonState.Enabled,
    shape: ButtonShape,

    ) {
    val (_, contentColor) = getStyleByHierarchyAndState(hierarchy, state)
    val padding = shape.getPillPaddingByButtonSize(buttonSize)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BaseButton(
            onClick = onClick,
            modifier = modifier,
            buttonShape = shape,
            hierarchy = hierarchy,
            buttonSize = buttonSize,
            state = state
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = padding)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (shape.leadingIcon != null) {
                        Icon(
                            modifier = Modifier.size(buttonSize.iconSize()),
                            imageVector = shape.leadingIcon!!,
                            tint = contentColor,
                            contentDescription = null
                        )
                    }
                    if (text != null) {
                        DsText(
                            text = shape.getTextByButtonType(text),
                            textAlign = TextAlign.Center,
                            contentColor = contentColor,
                            typography = buttonSize.typography()
                        )
                    }
                    if (shape.trailingIcon != null) {
                        Icon(
                            modifier = Modifier.size(buttonSize.iconSize()),
                            imageVector = shape.trailingIcon!!,
                            tint = contentColor,
                            contentDescription = null
                        )
                    }
                }
            }
        }
        if (shape.labelBottom != null && state != ButtonState.Loading) {
            DsText(
                modifier = Modifier.padding(top = DsTheme.spacers.space2x),
                text = shape.labelBottom!!,
                contentColor = getLabelBottomColor(state),
                textAlign = TextAlign.Center,
                typography = buttonSize.typography()
            )
        }
    }
}

private fun ButtonShape.getTextByButtonType(text: String): String {
    return when (this) {
        is ButtonShape.Rect, is ButtonShape.Pill -> text
        else -> text.subSequence(0, 3).toString()
    }
}

@Composable
private fun ButtonShape.getPillPaddingByButtonSize(size: ButtonSize): Dp {
    return when (this) {
        is ButtonShape.Pill -> when (size) {
            ButtonSize.Medium, ButtonSize.Large -> DsTheme.spacers.space4x
            else -> DsTheme.spacers.space2x
        }

        else -> 0.dp
    }
}
