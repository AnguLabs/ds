package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.control.Check
import angu.labs.ds.components.control.CheckBehavior
import angu.labs.ds.components.control.CheckState
import angu.labs.ds.components.control.Radio
import angu.labs.ds.components.control.RadioBehavior
import angu.labs.ds.components.control.RadioState
import angu.labs.ds.components.control.Switch
import angu.labs.ds.components.control.SwitchBehavior
import angu.labs.ds.components.control.SwitchState
import cafe.adriel.voyager.core.screen.Screen


internal object ControlScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val checkedState = remember { mutableStateOf(SwitchBehavior.Off) }
            Switch(
                behavior = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                switchSize = 48.dp,
            )

            Switch(
                behavior = checkedState.value,
                state = SwitchState.Disabled,
                onCheckedChange = { checkedState.value = it },
                switchSize = 48.dp,
            )
            val checkedCheckState = remember { mutableStateOf(CheckBehavior.Unselected) }
            val onClick: (CheckBehavior) -> Unit = {
                checkedCheckState.value = it
            }
            Row {

                Column {
                    Check(
                        state = CheckState.Enabled,
                        behavior = CheckBehavior.Selected,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Enabled,
                        behavior = CheckBehavior.Preselected,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Enabled,
                        behavior = CheckBehavior.Indeterminate,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Enabled,
                        behavior = CheckBehavior.Unselected,
                        onClick = onClick
                    )
                }
                Column {
                    Check(
                        state = CheckState.Disabled,
                        behavior = CheckBehavior.Selected,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Disabled,
                        behavior = CheckBehavior.Preselected,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Disabled,
                        behavior = CheckBehavior.Indeterminate,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Disabled,
                        behavior = CheckBehavior.Unselected,
                        onClick = onClick
                    )
                }
                Column {
                    Check(
                        state = CheckState.Error,
                        behavior = checkedCheckState.value,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Error,
                        behavior = CheckBehavior.Indeterminate,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Error,
                        behavior = CheckBehavior.Preselected,
                        onClick = onClick
                    )
                    Check(
                        state = CheckState.Error,
                        behavior = CheckBehavior.Unselected,
                        onClick = onClick
                    )
                }
            }
            val radio = remember { mutableStateOf(RadioBehavior.Selected) }

            Radio(
                behavior = radio.value,
                onSelect = { radio.value = it }
            )

            Radio(
                behavior = radio.value,
                onSelect = { },
                state = RadioState.Error
            )

            Radio(
                behavior = radio.value,
                onSelect = { },
                state = RadioState.Disabled
            )
        }
    }
}