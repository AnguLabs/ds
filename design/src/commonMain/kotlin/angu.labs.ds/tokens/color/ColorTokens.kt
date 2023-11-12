package angu.labs.ds.tokens.color

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
public data class ColorTokens(
    // Foundation
    val primaryA: Color,
    val primaryB: Color,
    val accent: Color,
    val negative: Color,
    val warning: Color,
    val positive: Color,
    // Core - Background
    val backgroundPrimary: Color,
    val backgroundInversePrimary: Color,
    val backgroundSecondary: Color,
    val backgroundInverseSecondary: Color,
    val backgroundTertiary: Color,
    // Core - Content
    val contentPrimary: Color,
    val contentInversePrimary: Color,
    val contentSecondary: Color,
    val contentInverseSecondary: Color,
    val contentTertiary: Color,
    val contentInverseTertiary: Color,
    // Core - Border
    val borderOpaque: Color,
    val borderInverseOpaque: Color,
    val borderTransparent: Color, // 8%
    val borderInverseTransparent: Color, // 20%
    val borderSelected: Color,
    val borderInverseSelected: Color,
    // Core - Extensions - Background
    val backgroundStateDisabled: Color,
    val backgroundOverlayArt: Color,
    val backgroundOverlayDark: Color,
    val backgroundOverlayLight: Color,
    val backgroundAccent: Color,
    val backgroundNegative: Color,
    val backgroundWarning: Color,
    val backgroundPositive: Color,
    val backgroundLightAccent: Color,
    val backgroundLightNegative: Color,
    val backgroundLightWarning: Color,
    val backgroundLightPositive: Color,
    val backgroundAlwaysDark: Color,
    val backgroundAlwaysLight: Color,
    // Core - Extensions - Content
    val contentStateDisabled: Color,
    val contentOnColor: Color,
    val contentOnColorInverse: Color,
    val contentAccent: Color,
    val contentNegative: Color,
    val contentWarning: Color,
    val contentPositive: Color,
    // Core - Extensions - Content
    val borderStateDisabled: Color,
    val borderAccent: Color,
    val borderAccentLight: Color,
    val borderNegative: Color,
    val borderWarning: Color,
    val borderPositive: Color,
    // Core - Extensions - Programs
    val safety: Color,
    val eats: Color,
    val freight: Color,
    val jump: Color,
    val rewardsTier1: Color,
    val rewardsTier2: Color,
    val rewardsTier3: Color,
    val rewardsTier4: Color,
    val membership: Color,
    val isDark : Boolean,
)