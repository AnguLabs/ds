package angu.labs.ds.components.control

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import angu.labs.ds.components.control.CheckState.Disabled
import angu.labs.ds.components.control.CheckState.Enabled
import angu.labs.ds.components.control.CheckState.Error
import angu.labs.ds.theme.DsTheme

@Composable
public fun Check(
    modifier: Modifier = Modifier,
    state: CheckState,
    behavior: CheckBehavior,
    onClick: ((CheckBehavior) -> Unit)? = null,
) {
    val style = getCheckStyle(state, behavior)
    Box(
        modifier = modifier.size(DsTheme.spacers.space12x),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(DsTheme.spacers.space6x)
                .background(style.boxColor)
                .then(
                    if (behavior != CheckBehavior.Preselected && state != Disabled) {
                        Modifier.clickable {
                            val newStateBehavior = when (behavior) {
                                CheckBehavior.Preselected -> CheckBehavior.Preselected
                                CheckBehavior.Selected -> CheckBehavior.Unselected
                                CheckBehavior.Indeterminate -> CheckBehavior.Unselected
                                CheckBehavior.Unselected -> CheckBehavior.Selected
                            }
                            onClick?.invoke(newStateBehavior)
                        }
                    } else Modifier
                )

        ) {
            Icon(
                imageVector = behavior.icon(),
                tint = style.iconColor,
                contentDescription = null,
                modifier = Modifier.size(DsTheme.spacers.space6x).background(style.boxColor)
            )
        }
    }
}

public enum class CheckState {
    Enabled, Disabled, Error
}

public enum class CheckBehavior(public val icon: @Composable () -> ImageVector) {
    Preselected({ DsTheme.icons.check }),
    Selected({ DsTheme.icons.check }),
    Indeterminate({ DsTheme.icons.minus }),
    Unselected({ DsTheme.icons.square }),
}

public data class CheckStyle(
    val iconColor: Color,
    val boxColor: Color,
)

@Composable
private fun getCheckStyle(state: CheckState, behavior: CheckBehavior): CheckStyle =
    with(DsTheme.colors) {
        fun getColor(): Color = when (state) {
            Enabled -> contentPrimary
            Disabled -> contentStateDisabled
            Error -> contentNegative
        }

        val (iconColor, boxColor) = when (behavior) {
            CheckBehavior.Preselected -> {
                val iconColor = when (state) {
                    Enabled -> contentPrimary
                    Disabled -> contentStateDisabled
                    Error -> contentNegative
                }
                iconColor to Color.Transparent
            }

            CheckBehavior.Indeterminate, CheckBehavior.Selected -> contentInversePrimary to getColor()

            CheckBehavior.Unselected -> {
                val iconColor = when (state) {
                    Enabled -> contentTertiary
                    Disabled -> contentStateDisabled
                    Error -> contentNegative
                }
                val boxColor = Color.Transparent
                iconColor to boxColor
            }
        }
        CheckStyle(iconColor, boxColor)
    }