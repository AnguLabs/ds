package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.text.DsText
import angu.labs.ds.tokens.typography.DsTypography
import cafe.adriel.voyager.core.screen.Screen


internal object LabelScreen : Screen {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            DsText(text = "Labels typography", typography = DsTypography.Heading.xSmall)
            DsTypography.Label.values.forEachIndexed { index, value ->
                DsText(text = "Label ${names[index]}", typography = value)
            }

            Spacer(modifier = Modifier.size(20.dp))
            DsText(text = "Paragraph typography", typography = DsTypography.Heading.xSmall)
            DsTypography.Paragraph.values.forEachIndexed { index, value ->
                DsText(text = "Paragraph ${names[index]}", typography = value)
            }

            Spacer(modifier = Modifier.size(20.dp))
            DsText(text = "Heading typography", typography = DsTypography.Heading.xSmall)
            DsTypography.Heading.values.forEachIndexed { index, value ->
                DsText(text = "Heading ${names[index]}", typography = value)
            }

            Spacer(modifier = Modifier.size(20.dp))
            DsText(text = "Display typography", typography = DsTypography.Heading.xSmall)
            DsTypography.Display.values.forEachIndexed { index, value ->
                DsText(text = "Label ${names[index]}", typography = value)
            }
        }
    }

    private val names: List<String> = listOf(
        "xSmall", "small", "medium", "large", "xLarge", "xxLarge", "xxxLarge"
    )
}