package angu.labs.ds.tokens.color

public val lightTokens: ColorTokens = ColorTokens(
    // Foundation
    accent = DsColor.blue400,
    primaryA = DsColor.black,
    primaryB = DsColor.white,
    negative = DsColor.red400,
    positive = DsColor.green400,
    warning = DsColor.yellow400,

    backgroundPrimary = DsColor.white,
    backgroundSecondary = DsColor.gray50,
    backgroundTertiary = DsColor.gray100,
    backgroundInversePrimary = DsColor.black,
    backgroundInverseSecondary = DsColor.gray800,

    // Content
    contentPrimary = DsColor.black,
    contentSecondary = DsColor.gray600,
    contentTertiary = DsColor.gray500,
    contentInversePrimary = DsColor.white,
    contentInverseSecondary = DsColor.gray300,
    contentInverseTertiary = DsColor.gray400,

    // Border
    borderOpaque = DsColor.gray200,
    borderTransparent = DsColor.black.alpha(0.08F),
    borderSelected = DsColor.black,
    borderInverseOpaque = DsColor.gray700,
    borderInverseTransparent = DsColor.white.alpha(0.2F),
    borderInverseSelected = DsColor.white,
    // Backgrounds
    backgroundStateDisabled = DsColor.gray50,
    backgroundOverlayDark = DsColor.black.alpha(0.3F),
    backgroundOverlayLight = DsColor.black.alpha(0.08F),
    backgroundOverlayArt = DsColor.black.alpha(0.00F),
    backgroundAccent = DsColor.blue400,
    backgroundNegative = DsColor.red400,
    backgroundWarning = DsColor.yellow400,
    backgroundPositive = DsColor.green400,
    backgroundLightAccent = DsColor.blue50,
    backgroundLightNegative = DsColor.red50,
    backgroundLightWarning = DsColor.yellow50,
    backgroundLightPositive = DsColor.green50,
    backgroundAlwaysDark = DsColor.black,
    backgroundAlwaysLight = DsColor.white,

    // Content
    contentStateDisabled = DsColor.gray400,
    contentAccent = DsColor.blue400,
    contentOnColor = DsColor.white,
    contentOnColorInverse = DsColor.black,
    contentNegative = DsColor.red400,
    contentWarning = DsColor.yellow600,
    contentPositive = DsColor.green400,

    // Border
    borderStateDisabled = DsColor.gray50,
    borderAccent = DsColor.blue400,
    borderAccentLight = DsColor.blue200,
    borderNegative = DsColor.red200,
    borderWarning = DsColor.yellow200,
    borderPositive = DsColor.green200,

    // Programs
    safety = DsColor.blue400,
    eats = DsColor.green400,
    freight = DsColor.cobalt400,
    jump = DsColor.red400,
    rewardsTier1 = DsColor.blue400,
    rewardsTier2 = DsColor.yellow400,
    rewardsTier3 = DsColor.platinum400,
    rewardsTier4 = DsColor.black,
    membership = DsColor.yellow600,

    isDark = false
)