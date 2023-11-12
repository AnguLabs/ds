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
import angu.labs.ds.components.control.SwitchBehavior.On
import angu.labs.ds.components.control.toggle
import angu.labs.ds.components.list.EndContentListItem
import angu.labs.ds.components.list.ListItem
import angu.labs.ds.components.list.ListTextContent
import angu.labs.ds.components.navbar.NavBar
import angu.labs.ds.components.navbar.NavBarType
import angu.labs.ds.components.navbar.NavbarAction
import angu.labs.ds.components.navbar.NavbarActionItem
import angu.labs.ds.components.navbar.defaultNavigation
import angu.labs.ds.tokens.dimens.Spacers
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Heart

internal object NavbarScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            var isExpanded by remember { mutableStateOf(On) }
            var isFixed by remember { mutableStateOf(On) }
            var isIconActions by remember { mutableStateOf(On) }
            var isIconNavStart by remember { mutableStateOf(On) }

            Spacer(modifier = Modifier.padding(top = Spacers.space2x))
            NavBar(
                title = "Navigation",
                startAction = if (isIconNavStart == On) defaultNavigation { } else buttonEndAction(),
                type = if (isFixed == On) NavBarType.Fixed(isExpanded == On) else NavBarType.Floating,
                endAction = if (isIconActions == On) iconEndAction() else buttonEndAction()
            )
            ListItem(
                textContent = ListTextContent(
                    startText = "Floating / Fixed",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = isFixed
                ),
                onClick = {
                    isFixed = isFixed.toggle()
                }
            )

            ListItem(
                textContent = ListTextContent(
                    startText = "Collapsed / Expanded",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = isExpanded
                ),
                onClick = {
                    isExpanded = isExpanded.toggle()
                }
            )

            ListItem(
                textContent = ListTextContent(
                    startText = "End (Icon / Button) Actions",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = isIconActions
                ),
                onClick = {
                    isIconActions = isIconActions.toggle()
                }
            )

            ListItem(
                showDivider = false,
                textContent = ListTextContent(
                    startText = "Start (Icon / Button) Actions",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = isIconNavStart
                ),
                onClick = {
                    isIconNavStart = isIconNavStart.toggle()
                }
            )
        }
    }

    private fun iconEndAction() = NavbarAction.Icons(
        action1 = NavbarActionItem(
            icon = { FeatherIcons.Heart },
            onClick = {}
        ),
        action2 = NavbarActionItem(
            icon = { FeatherIcons.Heart },
            onClick = {}
        )
    )

    private fun buttonEndAction() = NavbarAction.Button(
        text = "Action",
        onClick = {}
    )
}