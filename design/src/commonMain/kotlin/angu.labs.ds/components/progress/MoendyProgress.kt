package angu.labs.ds.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import angu.labs.ds.theme.DsTheme

@Composable
public fun ProgressStatic(
    modifier: Modifier = Modifier,
    color: Color = DsTheme.colors.accent,
    backgroundColor: Color = DsTheme.colors.backgroundTertiary,
    progress: Double,
) {
    BoxWithConstraints(modifier = modifier) {
        val width = maxWidth.value
        val progressNormalized =
            if (progress > width) normalize(progress, width) else scaleValue(progress, width)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(backgroundColor)
        ) {

            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(progressNormalized.dp)
                    .background(color)
            )
        }
    }
}

private fun normalize(value: Double, maxValue: Float): Double {
    val divisor = if ((maxValue - 0.1) <= 0.0) 0.1 else maxValue - 0.1
    return (value - 0.1) / divisor * 100
}

private fun scaleValue(value: Double, maxValue: Float): Double {
    val valueSanitized = when {
        value <= 0.0 -> 0.1
        value in 0.01..100.0 -> value
        else -> 100.0
    }
    return (valueSanitized / 100) * (maxValue)
}