package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import angu.labs.ds.components.button.ButtonHierarchy
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.components.button.ButtonSize
import angu.labs.ds.components.button.ButtonState
import angu.labs.ds.components.button.DsButton
import angu.labs.ds.components.control.SwitchBehavior.On
import angu.labs.ds.components.control.toggle
import angu.labs.ds.components.input.TextField
import angu.labs.ds.components.input.TextFieldSize
import angu.labs.ds.components.input.TextFieldState
import angu.labs.ds.components.list.EndContentListItem
import angu.labs.ds.components.list.ListItem
import angu.labs.ds.components.list.ListTextContent
import angu.labs.ds.theme.DsTheme
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Heart

public object TextFieldScreen : Screen {

    @Composable
    override fun Content() {
        var text by remember { mutableStateOf("") }
        var isComplete by remember { mutableStateOf(false) }
        var isIncomplete by remember { mutableStateOf(false) }
        var textFieldState by remember { mutableStateOf(TextFieldState.Active) }
        var switchBehavior by remember { mutableStateOf(On) }
        var switchBehaviorHint by remember { mutableStateOf(On) }
        var showIconBehaviorSwitch by remember { mutableStateOf(On) }
        var showSupportText by remember { mutableStateOf(On) }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            TextField(
                modifier = Modifier.padding(DsTheme.spacers.space4x),
                labelIcon = if (switchBehavior == On) FeatherIcons.Heart else null,
                maxCount = if (switchBehavior == On) 20 else null,
                onValueChange = { text = it },
                label = if (switchBehavior == On) "Nice label" else null,
                placeHolder = "Place Holder",
                hint = if (switchBehaviorHint == On) "Hint" else null,
                startIcon = if (showIconBehaviorSwitch == On) FeatherIcons.Heart else null,
                startLabel = if (showSupportText == On) "$" else null,
                endLabel = if (showSupportText == On) "$" else null,
                text = text,
                state = textFieldState,
                endIcon = if (showIconBehaviorSwitch == On) FeatherIcons.Heart else null,
                onStartIconClick = {},
                isIncomplete = isIncomplete,
                onEndIconClick = {},
                isComplete = isComplete,
                hintIcon = if (switchBehaviorHint == On) FeatherIcons.Heart else null,
                size = TextFieldSize.Large
            )

            Row {
                DsButton(
                    text = "Enabled",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = false
                        isIncomplete = false
                        textFieldState = TextFieldState.Enabled
                    }
                )

                DsButton(
                    text = "Error",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = false
                        isIncomplete = false
                        textFieldState = TextFieldState.Error
                    }
                )

                DsButton(
                    text = "Active",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = false
                        isIncomplete = false
                        textFieldState = TextFieldState.Active
                    }
                )

                DsButton(
                    text = "Disabled",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        text = "Disabled"
                        isComplete = false
                        isIncomplete = false
                        textFieldState = TextFieldState.Disabled
                    }
                )

                DsButton(
                    text = "Success",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = false
                        isIncomplete = false
                        textFieldState = TextFieldState.Success
                    }
                )
            }
            Row {
                DsButton(
                    text = "Complete",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = true
                        isIncomplete = false
                        textFieldState = TextFieldState.Active
                    }
                )
                DsButton(
                    text = "Incomplete",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = false
                        isIncomplete = true
                        textFieldState = TextFieldState.Active
                    }
                )

                DsButton(
                    text = "Loading",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = false
                        isIncomplete = false
                        textFieldState = TextFieldState.Loading
                    }
                )

                DsButton(
                    text = "ReadOnly",
                    type = ButtonShape.Pill(),
                    hierarchy = ButtonHierarchy.Primary,
                    buttonSize = ButtonSize.Small,
                    state = ButtonState.Enabled,
                    onClick = {
                        isComplete = false
                        isIncomplete = false
                        textFieldState = TextFieldState.ReadOnly
                    }
                )
            }

            ListItem(
                textContent = ListTextContent(
                    startText = "Show label",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = switchBehavior
                ),
                onClick = {
                    switchBehavior = switchBehavior.toggle()
                }
            )
            ListItem(
                textContent = ListTextContent(
                    startText = "Show hint",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = switchBehaviorHint
                ),
                onClick = {
                    switchBehaviorHint = switchBehaviorHint.toggle()
                }
            )

            ListItem(
                textContent = ListTextContent(
                    startText = "Show Icons",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = showIconBehaviorSwitch
                ),
                onClick = {
                    showIconBehaviorSwitch = showIconBehaviorSwitch.toggle()
                }
            )

            ListItem(
                showDivider = false,
                textContent = ListTextContent(
                    startText = "Show support texts",
                ),
                endContent = EndContentListItem.Switch(
                    behavior = showSupportText
                ),
                onClick = {
                    showSupportText = showSupportText.toggle()
                }
            )
        }
    }
}