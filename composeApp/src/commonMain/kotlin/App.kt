import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.button.ButtonHierarchy
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.DsIcons
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Check
import compose.icons.feathericons.ChevronDown
import compose.icons.feathericons.ChevronLeft
import compose.icons.feathericons.ChevronUp
import compose.icons.feathericons.ChevronsRight
import compose.icons.feathericons.Minus
import compose.icons.feathericons.Square
import compose.icons.feathericons.X

@Composable
fun App() {
    DsTheme(icons = defaultIcons) {
        Column {
            components.forEach {
                DsButton(
                    text = it,
                    type = ButtonShape.Rect(),
                    hierarchy = ButtonHierarchy.Tertiary,
                    onClick = { }
                )
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

private val components = listOf(
    "Buttons",
    "Labels",
    "List Head",
    "List item",
    "Artwork (avatar)",
    "Control",
    "Navbar",
    "Card",
    "Progress",
    "Input"
)