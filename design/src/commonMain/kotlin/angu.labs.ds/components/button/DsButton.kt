package angu.labs.ds.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun DsButton(
    text: String? = null,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    hierarchy: ButtonHierarchy = ButtonHierarchy.Primary,
    buttonSize: ButtonSize = ButtonSize.Medium,
    state: ButtonState = ButtonState.Enabled,
    type: ButtonShape
) {
    when (type) {
        is ButtonShape.Rect -> type.RectButton(
            text = text,
            modifier = modifier,
            onClick = onClick,
            hierarchy = hierarchy,
            leadingIcon = type.leadingIcon,
            trailingIcon = type.trailingIcon,
            buttonSize = buttonSize,
            state = state
        )

        else -> CommonButton(
            text = text,
            modifier = modifier,
            onClick = onClick,
            hierarchy = hierarchy.sanitizeFoNonRectButtons(),
            buttonSize = buttonSize,
            state = state,
            shape = type
        )
    }
}