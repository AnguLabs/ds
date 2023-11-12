package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.list.ListHeading
import angu.labs.ds.components.list.ListHeadingLabel
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Upload


internal object ListHeadingScreen : Screen {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListHeading(heading = "Heading", subHeading = "Sub heading")
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListHeading(
                heading = "Heading",
                subHeading = "Sub heading",
                label = ListHeadingLabel.PillLabel(text = "Button")
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListHeading(
                heading = "Heading",
                subHeading = "Sub heading",
                label = ListHeadingLabel.Text(label = "Label", "paragraph")
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListHeading(
                heading = "Heading",
                subHeading = "Sub heading",
                label = ListHeadingLabel.Circle(icon = FeatherIcons.Upload)
            )

            ListHeading(
                heading = "Heading",
                label = ListHeadingLabel.Text(label = "Label")
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
        }
    }
}