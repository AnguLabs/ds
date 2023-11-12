package angu.labs.ds.components.control

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.control.SwitchBehavior.Off
import angu.labs.ds.components.control.SwitchBehavior.On
import angu.labs.ds.components.control.SwitchState.Enabled
import angu.labs.ds.theme.DsTheme

@Composable
public fun Switch(
    behavior: SwitchBehavior = Off,
    state: SwitchState = Enabled,
    onCheckedChange: (SwitchBehavior) -> Unit,
    modifier: Modifier = Modifier,
    switchSize: Dp = 28.dp
) {
    val (backgroundColor, circleColor) = getStyleSwitchColor(state, behavior == On)
    Box(
        modifier = modifier
            .size(width = 52.dp, height = 48.dp)
            .padding(top = DsTheme.spacers.space2x, bottom = DsTheme.spacers.space2x),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(DsTheme.spacers.space8x)
                .clip(CircleShape)
                .clickable {
                    onCheckedChange(if (behavior == On) Off else On)
                },
            color = backgroundColor,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 2.dp, end = 2.dp),
                horizontalArrangement = if (behavior == On) Arrangement.End else Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .then(
                            if (behavior == Off && state == Enabled) Modifier.shadow(
                                4.dp,
                                CircleShape
                            ) else Modifier
                        )
                        .shadow(2.dp, CircleShape)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(color = circleColor)
                )
            }
        }
    }
}

@Composable
private fun getStyleSwitchColor(state: SwitchState, isChecked: Boolean): Pair<Color, Color> =
    with(DsTheme.colors) {
        when (state) {
            Enabled -> {
                val background = if (isChecked) backgroundInversePrimary else backgroundTertiary
                background to contentInversePrimary
            }

            SwitchState.Disabled -> backgroundStateDisabled to contentStateDisabled
        }
    }

public enum class SwitchState {
    Enabled, Disabled
}

public enum class SwitchBehavior {
    On, Off
}

public fun SwitchBehavior.toggle(): SwitchBehavior {
    return if (this == On) Off else On
}