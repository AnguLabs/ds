package angu.labs.ds.components.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.typography.DsTypography

@Composable
public fun DsText(
    text: String,
    modifier: Modifier = Modifier,
    typography: DsTypography,
    textAlign: TextAlign? = null,
    contentColor: Color = DsTheme.colors.contentPrimary,
    maxLines: Int = Int.MAX_VALUE,
) {
    BasicText(
        text = text,
        modifier = modifier,
        style = typography.style.copy(color = contentColor, textAlign = textAlign),
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines
    )
}