package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.artwork.ArtworkDiversifyColor
import angu.labs.ds.components.artwork.ArtworkSize
import angu.labs.ds.components.artwork.ArtworkType
import angu.labs.ds.components.control.SwitchBehavior
import angu.labs.ds.components.control.toggle
import angu.labs.ds.components.list.EndContentListItem
import angu.labs.ds.components.list.ListItem
import angu.labs.ds.components.list.ListItemType
import angu.labs.ds.components.list.ListTextContent
import angu.labs.ds.components.list.StartContentListItem
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Heart

internal object ListItemScreen : Screen {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.padding(top = 8.dp))

            var switchBehavior by remember { mutableStateOf(SwitchBehavior.Off) }
            ListItem(
                startContent = StartContentListItem(
                    type = ArtworkType.Icon(
                        color = ArtworkDiversifyColor.Default,
                        imageVector = FeatherIcons.Heart
                    ),
                    artworkSize = ArtworkSize.Medium,
                ),
                textContent = ListTextContent(
                    startText = "Label",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = switchBehavior
                ),
                onClick = {
                    switchBehavior = switchBehavior.toggle()
                }
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListItem(
                startContent = StartContentListItem(
                    type = ArtworkType.Icon(
                        color = ArtworkDiversifyColor.Default,
                        imageVector = FeatherIcons.Heart
                    ),
                    artworkSize = ArtworkSize.Medium,
                ),
                textContent = ListTextContent(
                    startText = "Caique",
                    startParagraph = "Paragraph",
                    endText = "Oliveira",
                    endParagraph = "End",
                    supportParagraph = "support"
                )
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ListItem(
                startContent = StartContentListItem(
                    type = ArtworkType.Icon(
                        color = ArtworkDiversifyColor.Default,
                        imageVector = FeatherIcons.Heart
                    ),
                    artworkSize = ArtworkSize.Medium,
                ),
                textContent = ListTextContent(startText = "Caique")
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ListItem(
                startContent = StartContentListItem(
                    type = ArtworkType.Icon(
                        color = ArtworkDiversifyColor.Default,
                        imageVector = FeatherIcons.Heart
                    ),
                    artworkSize = ArtworkSize.Medium,
                ),
                textContent = ListTextContent(
                    startText = "Caique",
                    startParagraph = "Paragraph",
                ),
                endContent = EndContentListItem.Chevron()
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ListItem(
                startContent = StartContentListItem(
                    type = ArtworkType.Icon(
                        color = ArtworkDiversifyColor.Default,
                        imageVector = FeatherIcons.Heart
                    ),
                    artworkSize = ArtworkSize.Medium,
                ),
                textContent = ListTextContent(
                    startText = "Caique asduahsd jah asjh asj hasj has jahs ajsh aj has jhas jha ajsh asjh as jhas jahs ",
                    startParagraph = "Paragraph",
                ),
                endContent = EndContentListItem.Action(
                    action1 = EndContentListItem.ActionItem(
                        icon = FeatherIcons.Heart,
                        onClick = {}
                    ),
                    action2 = EndContentListItem.ActionItem(
                        icon = FeatherIcons.Heart,
                        onClick = {}
                    ),
                ),
                onClick = {}
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ListItem(
                textContent = ListTextContent(
                    startText = "Caique asduahsd jah asjh asj hasj has jahs ajsh aj has jhas jha ajsh asjh as jhas jahs ",
                    startParagraph = "Paragraph",
                ),
                endContent = EndContentListItem.Action(
                    action1 = EndContentListItem.ActionItem(
                        icon = FeatherIcons.Heart,
                        onClick = {}
                    ),
                    action2 = EndContentListItem.ActionItem(
                        icon = FeatherIcons.Heart,
                        onClick = {}
                    ),
                ),
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ListItem(
                startContent = StartContentListItem(
                    type = ArtworkType.Icon(
                        color = ArtworkDiversifyColor.Default,
                        imageVector = FeatherIcons.Heart
                    ),
                    artworkSize = ArtworkSize.Medium,
                ),
                textContent = ListTextContent(
                    startText = "Caique asduahsd jah asjh asj hasj has jahs ajsh aj has jhas jha ajsh asjh as jhas jahs ",
                    startParagraph = "Paragraph",
                    endText = "Oliveira",
                    endParagraph = "End",
                    supportParagraph = "support"
                ),
                endContent = EndContentListItem.ActionText("Action", onClick = {
                    println("clicou!!!")
                }),
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ListItem(
                textContent = ListTextContent(
                    startText = "Caique asduahsd jah asjh asj hasj has jahs ajsh aj has jhas jha ajsh asjh as jhas jahs ",
                    startParagraph = "Paragraph",
                    endText = "Oliveira",
                    endParagraph = "End",
                    supportParagraph = "support  asd  asd a asd a asd asd asd asd asd asd asd asd a asd asd as a"
                ),
                endContent = EndContentListItem.ActionText("Action", onClick = {
                    println("clicou!!!")
                }),
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListItem(
                textContent = ListTextContent(
                    startText = "Caique asduahsd jah asjh asj hasj has jahs ajsh aj has jhas jha ajsh asjh as jhas jahs ",
                    startParagraph = "Paragraph",
                    endText = "Oliveira",
                    endParagraph = "End",
                    supportParagraph = "support  asd  asd a asd a asd asd asd asd asd asd asd asd a asd asd as a"
                ),
                endContent = EndContentListItem.ActionText("Action", onClick = {
                    println("clicou!!!")
                }),
                type = ListItemType.Compact
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListItem(
                textContent = ListTextContent(
                    startText = "Caique asduahsd jah",
                ),
                endContent = EndContentListItem.ActionText("Action", onClick = {
                    println("clicou!!!")
                }),
                type = ListItemType.Compact
            )
        }
    }
}