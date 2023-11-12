package angu.labs.ds.components.control

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.control.RadioBehavior.Selected
import angu.labs.ds.components.control.RadioBehavior.Unselected
import angu.labs.ds.theme.DsTheme

@Composable
public fun Radio(
    behavior: RadioBehavior = Unselected,
    onSelect: ((RadioBehavior) -> Unit)? = null,
    state: RadioState = RadioState.Enabled
) {
    Column(
        modifier =
        Modifier
            .size(DsTheme.spacers.space12x)
            .clip(CircleShape)
            .background(Color.Transparent)
            .padding(12.dp)
            .clip(CircleShape)
            .then(
                if (onSelect != null && state != RadioState.Disabled) {
                    Modifier.clickable {
                        val newState = if (behavior == Selected) Unselected else Selected
                        onSelect(newState)
                    }
                } else Modifier
            )
            .background(state.color())
            .padding(if (behavior == Selected) 8.dp else 3.dp)
            .clip(CircleShape)
            .background(DsTheme.colors.contentInversePrimary)
    ) {}
}

public enum class RadioBehavior {
    Selected, Unselected
}

public enum class RadioState(
    public val color: @Composable () -> Color
) {
    Enabled({ DsTheme.colors.contentPrimary }),
    Disabled({ DsTheme.colors.contentStateDisabled }),
    Error({ DsTheme.colors.contentNegative })
}