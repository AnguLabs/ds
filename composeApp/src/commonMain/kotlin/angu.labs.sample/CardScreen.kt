package angu.labs.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.card.Card
import angu.labs.ds.components.card.CardButtonTier
import angu.labs.ds.components.card.CardMediaContent
import angu.labs.ds.components.card.FixedTierContent
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.typography.DsTypography
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Bluetooth
import compose.icons.feathericons.Heart
import compose.icons.feathericons.Square

internal object CardScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.padding(horizontal = 16.dp),
                fixedContent = FixedTierContent(
                    eyeBrowIcon = FeatherIcons.Square,
                    eyeBrowText = "Label asd a sd asd ad asd a asd asd asd asd asd asd asd as asd asd as as dsd ad ttt t t t t t t t t",
                    hasCloseIcon = true,
                    onClickClose = {},
                    headline = "Headline",
                    artwork = FeatherIcons.Bluetooth,
                    paragraph = "Paragraph",
                    currency = "Currency"
                ),
                buttonTier = CardButtonTier(
                    text = "Action",
                    onClick = {},
                    buttonTierAlignment = CardButtonTier.Alignment.CenterFullWidth
                )
            )

            Card(
                modifier = Modifier.padding(16.dp),
                fixedContent = FixedTierContent(
                    eyeBrowText = "Chris Boba Restaurant",
                    hasCloseIcon = true,
                    onClickClose = {},
                    headline = "Delivered",
                    paragraph = "Leave feedback about your meal and delivery. You can also add a tip for Kayvan.",
                    progress = 1000.0
                ),
                buttonTier = CardButtonTier(
                    text = "Rate Order",
                    onClick = {},
                    buttonTierAlignment = CardButtonTier.Alignment.Left
                )
            )


            Card(
                modifier = Modifier.padding(16.dp),
                fixedContent = FixedTierContent(
                    eyeBrowText = "Chris Boba Restaurant",
                    hasCloseIcon = true,
                    onClickClose = {},
                    headline = "Delivered",
                    paragraph = "Leave feedback about your meal and delivery. You can also add a tip for Kayvan.",
                ),
                mediaContent = CardMediaContent(
                    modifier = Modifier.clip(RoundedCornerShape(2.dp)),
                    size = CardMediaContent.MediaContentSize.Large,
                    layoutType = CardMediaContent.MediaContentLayout.Bleed,
                    actionIconClick = {},
                    actionIcon = FeatherIcons.Heart,
                    position = CardMediaContent.CardMediaContentPosition.Middle
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DsTheme.colors.backgroundTertiary),
                        contentAlignment = Alignment.Center
                    ) {
                        DsText(
                            text = "Imagine um gráfico aqui",
                            typography = DsTypography.Label.medium
                        )
                    }
                },
                buttonTier = CardButtonTier(
                    text = "Rate Order",
                    onClick = {},
                    buttonTierAlignment = CardButtonTier.Alignment.Left
                )
            )

            Card(
                modifier = Modifier.padding(16.dp),
                fixedContent = FixedTierContent(
                    eyeBrowText = "Chris Boba Restaurant",
                    hasCloseIcon = true,
                    onClickClose = {},
                    headline = "Delivered",
                    paragraph = "Leave feedback about your meal and delivery. You can also add a tip for Kayvan.",
                ),
                mediaContent = CardMediaContent(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                    size = CardMediaContent.MediaContentSize.Large,
                    layoutType = CardMediaContent.MediaContentLayout.Bleed,
                    actionIconClick = {},
                    actionIcon = FeatherIcons.Heart,
                    position = CardMediaContent.CardMediaContentPosition.Top
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DsTheme.colors.backgroundTertiary),
                        contentAlignment = Alignment.Center
                    ) {
                        DsText(
                            text = "Imagine um gráfico aqui",
                            typography = DsTypography.Label.medium
                        )
                    }
                },
                buttonTier = CardButtonTier(
                    text = "Rate Order",
                    onClick = {},
                    buttonTierAlignment = CardButtonTier.Alignment.Left
                )
            )
        }
    }
}