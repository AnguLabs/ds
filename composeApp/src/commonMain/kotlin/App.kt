import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.DsIcons
import angu.labs.sample.ComponentsScreen
import angu.labs.sample.ThemeState
import cafe.adriel.voyager.navigator.Navigator
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Check
import compose.icons.feathericons.ChevronDown
import compose.icons.feathericons.ChevronLeft
import compose.icons.feathericons.ChevronUp
import compose.icons.feathericons.ChevronsRight
import compose.icons.feathericons.Minus
import compose.icons.feathericons.Moon
import compose.icons.feathericons.Square
import compose.icons.feathericons.Sun
import compose.icons.feathericons.X

@Composable
fun App() {
    DsTheme(
        darkTheme = ThemeState.darkModeState.value,
        icons = defaultIcons,
    ) {
        val isDark = ThemeState.darkModeState.value
        Box(
            modifier = Modifier
                .background(DsTheme.colors.backgroundPrimary)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Storybook",
                            color = DsTheme.colors.contentPrimary
                        )
                    },
                    backgroundColor = DsTheme.colors.backgroundPrimary,
                    actions = {
                        Icon(
                            modifier = Modifier
                                .clickable { ThemeState.darkModeState.value = !isDark }
                                .padding(DsTheme.spacers.space1x),
                            imageVector = if (isDark) FeatherIcons.Sun else FeatherIcons.Moon,
                            contentDescription = null,
                            tint = DsTheme.colors.contentPrimary
                        )
                    }
                )
                Column {
                    Navigator(screen = ComponentsScreen)
                }
            }
        }
    }
}

private val defaultIcons = DsIcons(
    x = FeatherIcons.X,
    check = FeatherIcons.Check,
    arrowLeft = FeatherIcons.ArrowLeft,
    chevronDown = FeatherIcons.ChevronDown,
    chevronUp = FeatherIcons.ChevronUp,
    chevronLeft = FeatherIcons.ChevronLeft,
    chevronRight = FeatherIcons.ChevronsRight,
    minus = FeatherIcons.Minus,
    square = FeatherIcons.Square
)
