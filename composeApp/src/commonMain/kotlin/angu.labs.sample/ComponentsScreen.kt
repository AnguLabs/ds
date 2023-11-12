package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import angu.labs.ds.components.button.ButtonHierarchy
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.components.button.DsButton
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal object ComponentsScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column {
            components.forEach { (label, screen) ->
                DsButton(
                    text = label,
                    type = ButtonShape.Rect(),
                    hierarchy = ButtonHierarchy.Tertiary,
                    onClick = { navigator.push(screen) }
                )
            }
        }
    }
}


private val components = listOf(
    "Buttons" to ButtonsScreen,
    "Labels" to LabelScreen,
    "List Head" to ListHeadingScreen,
    "List item" to ListItemScreen,
    "Artwork (avatar)" to ArtWorkScreen,
    "Control" to ControlScreen,
    "Navbar" to NavbarScreen,
    "Card" to CardScreen,
    "Progress" to ProgressScreen,
    "Input" to TextFieldScreen
)