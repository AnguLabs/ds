package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.progress.ProgressStatic
import angu.labs.ds.theme.DsTheme
import cafe.adriel.voyager.core.screen.Screen

internal object ProgressScreen : Screen {

    @Composable
    override fun Content() {
        Column {
            ProgressStatic(progress = 20.0)
            ProgressStatic(
                modifier = Modifier.padding(top = 16.dp),
                progress = 40.0,
                color = DsTheme.colors.membership
            )
        }
    }
}