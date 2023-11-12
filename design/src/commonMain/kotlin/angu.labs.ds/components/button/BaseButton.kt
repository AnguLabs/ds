package angu.labs.ds.components.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.button.ButtonShape.Pill
import angu.labs.ds.components.button.ButtonShape.Rect
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.typography.DsTypography

@Composable
internal fun BaseButton(
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    hierarchy: ButtonHierarchy,
    buttonShape: ButtonShape,
    buttonSize: ButtonSize = ButtonSize.Medium,
    state: ButtonState = ButtonState.Enabled,
    content: (@Composable BoxScope.() -> Unit),
) {
    val interaction = remember { MutableInteractionSource() }
    val minWidth =
        if (buttonShape is Pill) buttonSize.height() + DsTheme.spacers.space9x else buttonSize.height()
    val enabled = state == ButtonState.Enabled
    val (background, _) = getStyleByHierarchyAndState(hierarchy, state)
    Box(
        modifier = Modifier.defaultMinSize(
            minWidth = buttonSize.containerSize(),
            minHeight = buttonSize.containerSize()
        ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .then(if (buttonShape is Rect) Modifier.fillMaxWidth() else Modifier)
                .defaultMinSize(
                    minHeight = buttonSize.height(),
                    minWidth = minWidth
                )
                .clip(buttonShape.shape())
                .then(
                    if (enabled && onClick != null) {
                        Modifier.clickable(
                            interactionSource = interaction,
                            indication = rememberRipple(),
                            enabled = enabled,
                            onClick = onClick,
                            onClickLabel = null
                        )
                    } else {
                        Modifier
                    },
                ).semantics {
                    role = Role.Button
                }.background(background),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(state == ButtonState.Loading) {
                Row(modifier = Modifier.align(Alignment.Center)) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(buttonSize.iconSize()),
                        color = DsTheme.colors.borderAccent,
                        backgroundColor = DsTheme.colors.contentTertiary,
                        strokeWidth = 2.5.dp
                    )
                }
            }
            AnimatedVisibility(state != ButtonState.Loading) {
                content()
            }
        }
    }
}

public sealed class ButtonShape(
    public open val shape: @Composable () -> Shape,
    public open val leadingIcon: ImageVector? = null,
    public open val trailingIcon: ImageVector? = null,
    public open val labelBottom: String? = null,
) {
    public data class Rect(
        public override val leadingIcon: ImageVector? = null,
        public override val trailingIcon: ImageVector? = null,
    ) : ButtonShape(
        shape = { DsTheme.shapes.medium },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon, null
    )

    public data class Pill(
        public override val leadingIcon: ImageVector? = null,
        public override val trailingIcon: ImageVector? = null,
    ) : ButtonShape(
        shape = { DsTheme.shapes.circle },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )

    public data class Circle(
        public val icon: ImageVector? = null,
        public override val labelBottom: String? = null,
    ) : ButtonShape(
        shape = { DsTheme.shapes.circle },
        leadingIcon = icon,
        labelBottom = labelBottom
    )

    public data class Square(
        public val icon: ImageVector? = null,
        public override val labelBottom: String? = null,
    ) : ButtonShape(
        shape = { DsTheme.shapes.medium },
        leadingIcon = icon,
        labelBottom = labelBottom
    )
}

internal fun ButtonHierarchy.sanitizeFoNonRectButtons(): ButtonHierarchy {
    return when (this) {
        ButtonHierarchy.Primary, ButtonHierarchy.Secondary -> ButtonHierarchy.Secondary
        else -> ButtonHierarchy.Tertiary
    }
}

public enum class ButtonSize(
    public val height: @Composable () -> Dp,
    public val iconSize: @Composable () -> Dp,
    public val containerSize: @Composable () -> Dp,
    public val typography: @Composable () -> DsTypography,
) {
    Large(
        height = { DsTheme.spacers.space14x },
        iconSize = { DsTheme.spacers.space7x },
        containerSize = { DsTheme.spacers.space14x },
        typography = { DsTypography.Label.large }
    ),
    Medium(
        height = { DsTheme.spacers.space12x },
        iconSize = { DsTheme.spacers.space5x },
        containerSize = { DsTheme.spacers.space12x },
        typography = { DsTypography.Label.medium }
    ),
    Small(
        height = { DsTheme.spacers.space9x },
        iconSize = { DsTheme.spacers.space4x },
        containerSize = { DsTheme.spacers.space12x },
        typography = { DsTypography.Label.small }
    ),
    XSmall(
        height = { DsTheme.spacers.space7x },
        iconSize = { DsTheme.spacers.space3x },
        containerSize = { DsTheme.spacers.space12x },
        typography = { DsTypography.Label.xSmall }
    )
}

//https://base.uber.com/6d2425e9f/p/756216-button/t/47fbcf
public enum class ButtonHierarchy {
    Primary,
    Secondary,
    Tertiary
}

public enum class ButtonState {
    Enabled, Disabled, Loading, Active
}

@Composable
internal fun getStyleByHierarchyAndState(
    hierarchy: ButtonHierarchy,
    state: ButtonState
): ButtonColors {
    return when (hierarchy) {
        ButtonHierarchy.Primary -> getColorForPrimaryHierarchy(state)
        ButtonHierarchy.Secondary -> getColorForSecondaryHierarchy(state)
        ButtonHierarchy.Tertiary -> getColorForTertiaryHierarchy(state)
    }
}

@Composable
internal fun getColorForPrimaryHierarchy(state: ButtonState): ButtonColors {
    return with(DsTheme.colors) {
        val (bg, content) = when (state) {
            ButtonState.Enabled -> backgroundInversePrimary to contentInversePrimary
            ButtonState.Disabled -> backgroundStateDisabled to contentStateDisabled
            ButtonState.Loading -> backgroundInversePrimary to contentInversePrimary
            ButtonState.Active -> backgroundInversePrimary to contentInversePrimary
        }
        ButtonColors(background = bg, contentColor = content)
    }
}

@Composable
internal fun getColorForSecondaryHierarchy(state: ButtonState): ButtonColors {
    return with(DsTheme.colors) {
        val (bg, content) = when (state) {
            ButtonState.Enabled -> backgroundTertiary to contentPrimary
            ButtonState.Disabled -> backgroundStateDisabled to contentStateDisabled
            ButtonState.Loading -> backgroundTertiary to contentPrimary
            ButtonState.Active -> backgroundInversePrimary to contentInversePrimary
        }
        ButtonColors(background = bg, contentColor = content)
    }
}

@Composable
internal fun getColorForTertiaryHierarchy(state: ButtonState): ButtonColors {
    return with(DsTheme.colors) {
        val (bg, content) = when (state) {
            ButtonState.Enabled -> Color.Transparent to contentPrimary
            ButtonState.Disabled -> backgroundStateDisabled to contentPrimary
            ButtonState.Loading -> Color.Transparent to contentPrimary
            ButtonState.Active -> backgroundInversePrimary to contentInversePrimary
        }
        ButtonColors(background = bg, contentColor = content)
    }
}


internal data class ButtonColors(
    val background: Color,
    val contentColor: Color
)

@Composable
internal fun getLabelBottomColor(state: ButtonState): Color {
    return when (state) {
        ButtonState.Enabled -> DsTheme.colors.contentPrimary
        ButtonState.Disabled -> DsTheme.colors.contentStateDisabled
        else -> Color.Transparent
    }
}