package angu.labs.ds.components.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.theme.DsTheme

@Composable
public fun DsDivider(
    modifier: Modifier,
    type: DividerType
) {
    val color = with(DsTheme.colors) {
        if (type == DividerType.Module) backgroundTertiary else borderOpaque
    }
    Box(
        modifier = Modifier.background(color)
            .fillMaxWidth()
            .height(type.height)
    )
}

public enum class DividerType(public val height: Dp) {
    Cell(1.dp), Section(2.dp), Module(8.dp)
}