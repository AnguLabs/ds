package angu.labs.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.artwork.Artwork
import angu.labs.ds.components.artwork.ArtworkDiversifyColor
import angu.labs.ds.components.artwork.ArtworkSize
import angu.labs.ds.components.artwork.ArtworkType
import angu.labs.ds.components.text.DsText
import angu.labs.ds.tokens.typography.DsTypography
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Upload

object ArtWorkScreen : Screen {

    @Composable
    override fun Content() {
        Column {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row {
                Artwork(
                    type = ArtworkType.Letter(
                        text = "C",
                        color = ArtworkDiversifyColor.Blue
                    ),
                    artworkSize = ArtworkSize.Medium
                )

                Artwork(
                    type = ArtworkType.Letter(
                        text = "CC",
                        color = ArtworkDiversifyColor.Red
                    ),
                    artworkSize = ArtworkSize.Small
                )

                Artwork(
                    type = ArtworkType.Letter(
                        text = "CC",
                        color = ArtworkDiversifyColor.Red
                    ),
                    artworkSize = ArtworkSize.XSmall
                )

            }
            Row {

                Artwork(
                    type = ArtworkType.Icon(
                        imageVector = FeatherIcons.Upload,
                        color = ArtworkDiversifyColor.Red
                    ),
                    artworkSize = ArtworkSize.Medium
                )

                Artwork(
                    type = ArtworkType.Icon(
                        imageVector = FeatherIcons.Upload,
                        color = ArtworkDiversifyColor.Red
                    ),
                    artworkSize = ArtworkSize.Small
                )
                Artwork(
                    type = ArtworkType.Icon(
                        imageVector = FeatherIcons.Upload,
                        color = ArtworkDiversifyColor.Red
                    ),
                    artworkSize = ArtworkSize.XSmall
                )
            }
            DsText(text = "Diversify colors", typography = DsTypography.Heading.small)
            Row {
                ArtworkDiversifyColor.values().forEach {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(it.background())
                    )
                }
            }
        }
    }
}