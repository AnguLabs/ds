package angu.labs.ds.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.typography.DsTypography

@Composable
public fun ProgressIndicator(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Column(
        modifier = modifier
            .padding(DsTheme.spacers.space4x)
            .clip(RoundedCornerShape(DsTheme.spacers.space2x))
            .background(DsTheme.colors.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (text != null) {
            DsText(
                modifier = Modifier
                    .padding(horizontal = DsTheme.spacers.space4x)
                    .padding(bottom = DsTheme.spacers.space2x),
                text = text,
                typography = DsTypography.Paragraph.large,
                textAlign = TextAlign.Center
            )
        }
        LinearProgressIndicator(
            modifier = Modifier
                .padding(horizontal = DsTheme.spacers.space4x)
                .fillMaxWidth(),
            color = DsTheme.colors.contentPrimary,
            backgroundColor = DsTheme.colors.backgroundPrimary
        )
    }
}