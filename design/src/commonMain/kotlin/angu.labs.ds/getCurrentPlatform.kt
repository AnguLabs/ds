package angu.labs.ds

import androidx.compose.ui.unit.Dp
import angu.labs.ds.tokens.dimens.Spacers

public expect fun getCurrentPlatform(): Platform

public enum class Platform(
    public val height: Dp
) {
    IOS(Spacers.space11x),
    Android(Spacers.space12x),
    Desktop(Spacers.space12x)
}