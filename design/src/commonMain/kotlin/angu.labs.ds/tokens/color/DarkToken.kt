package angu.labs.ds.tokens.color

public val darkTokens: ColorTokens = ColorTokens(
    // Foundation
    accent = DsColor.blue400,
    primaryA = DsColor.gray200,
    primaryB = DsColor.gray900,
    negative = DsColor.red500,
    positive = DsColor.green500,
    warning = DsColor.yellow500,

    //background
    backgroundPrimary = DsColor.gray900,
    backgroundSecondary = DsColor.gray800,
    backgroundTertiary = DsColor.gray700,
    backgroundInversePrimary = DsColor.gray200,
    backgroundInverseSecondary = DsColor.gray300,

    //content
    contentPrimary = DsColor.white,
    contentSecondary = DsColor.gray300,
    contentTertiary = DsColor.gray400,
    contentInversePrimary = DsColor.black,
    contentInverseSecondary = DsColor.gray700,
    contentInverseTertiary = DsColor.gray600,

    //border
    borderOpaque = DsColor.gray700,
    borderTransparent = DsColor.gray200.alpha(0.08F),
    borderSelected = DsColor.gray200,
    borderInverseOpaque = DsColor.gray400,
    borderInverseTransparent = DsColor.gray900.alpha(0.2F),
    borderInverseSelected = DsColor.gray900,

    //extensions - background
    backgroundStateDisabled = DsColor.gray800,
    backgroundOverlayDark = DsColor.black.alpha(0.3F),
    backgroundOverlayLight = DsColor.black.alpha(0.08F),
    backgroundOverlayArt = DsColor.black.alpha(0.16F),
    backgroundAccent = DsColor.blue400,
    backgroundNegative = DsColor.red500,
    backgroundWarning = DsColor.yellow500,
    backgroundPositive = DsColor.green500,
    backgroundLightAccent = DsColor.blue700,
    backgroundLightPositive = DsColor.green700,
    backgroundLightNegative = DsColor.red700,
    backgroundLightWarning = DsColor.yellow700,
    backgroundAlwaysDark = DsColor.gray900,
    backgroundAlwaysLight = DsColor.gray100,

    //extensions - content
    contentStateDisabled = DsColor.gray600,
    contentAccent = DsColor.blue300,
    contentOnColor = DsColor.white,
    contentOnColorInverse = DsColor.black,
    contentNegative = DsColor.red300,
    contentWarning = DsColor.yellow300,
    contentPositive = DsColor.green300,

    // extensions - border
    borderStateDisabled = DsColor.gray800,
    borderAccent = DsColor.blue400,
    borderAccentLight = DsColor.blue500,
    borderNegative = DsColor.red500,
    borderWarning = DsColor.yellow500,
    borderPositive = DsColor.green500,

    // extension - programs
    safety = DsColor.blue400,
    eats = DsColor.green400,
    freight = DsColor.cobalt400,
    jump = DsColor.red400,
    rewardsTier1 = DsColor.blue400,
    rewardsTier2 = DsColor.yellow400,
    rewardsTier3 = DsColor.platinum400,
    rewardsTier4 = DsColor.gray200,
    membership = DsColor.yellow600,

    isDark = true
)