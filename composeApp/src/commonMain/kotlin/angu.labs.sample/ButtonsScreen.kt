package angu.labs.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.button.ButtonHierarchy
import angu.labs.ds.components.button.ButtonShape
import angu.labs.ds.components.button.ButtonState
import angu.labs.ds.components.button.DsButton
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.FeatherIcons
import compose.icons.feathericons.Archive
import compose.icons.feathericons.Box

object ButtonsScreen : Screen {

    @Composable
    override fun Content() {
        Column {
            DsButton(
                text = "Moendy label",
                modifier = Modifier,
                onClick = {},
                hierarchy = ButtonHierarchy.Primary,
                state = ButtonState.Loading,
                type = ButtonShape.Rect(
                    leadingIcon = FeatherIcons.Archive,
                    trailingIcon = FeatherIcons.Box,
                )
            )
            Spacer(Modifier.padding(4.dp))

            DsButton(
                text = "Btn Primary",
                modifier = Modifier,
                onClick = {},
                type = ButtonShape.Rect(
                    leadingIcon = FeatherIcons.Archive,
                    trailingIcon = FeatherIcons.Box,
                ),
                hierarchy = ButtonHierarchy.Primary,
            )
            Spacer(Modifier.padding(4.dp))

            DsButton(
                text = "Moendy Primary Disabled",
                modifier = Modifier,
                onClick = {},
                type = ButtonShape.Rect(
                    leadingIcon = FeatherIcons.Archive,
                    trailingIcon = FeatherIcons.Box,
                ),
                hierarchy = ButtonHierarchy.Primary,
                state = ButtonState.Disabled
            )
            Spacer(Modifier.padding(4.dp))

            DsButton(
                text = "Moendy label",
                modifier = Modifier,
                onClick = {},
                type = ButtonShape.Rect(
                    leadingIcon = FeatherIcons.Archive,
                    trailingIcon = FeatherIcons.Box,
                ),
                hierarchy = ButtonHierarchy.Secondary,
            )
            Spacer(Modifier.padding(4.dp))
            DsButton(
                text = "Moendy label",
                modifier = Modifier,
                onClick = {},
                type = ButtonShape.Rect(
                    leadingIcon = FeatherIcons.Archive,
                    trailingIcon = FeatherIcons.Box,
                ),
                hierarchy = ButtonHierarchy.Tertiary,
            )
            Spacer(Modifier.padding(4.dp))
            DsButton(
                text = "Label",
                onClick = {},
                type = ButtonShape.Pill(
                    leadingIcon = FeatherIcons.Archive,
                    trailingIcon = FeatherIcons.Box,
                ),
                hierarchy = ButtonHierarchy.Secondary
            )
            Spacer(Modifier.padding(4.dp))
            DsButton(
                text = "Moendy Pill",
                onClick = {},
                type = ButtonShape.Circle(
                    icon = FeatherIcons.Archive,
                    labelBottom = "Circle Btn",
                ),
                state = ButtonState.Loading
            )
            Spacer(Modifier.padding(4.dp))
            DsButton(
                text = "Moendy Pill",
                onClick = {},
                type = ButtonShape.Square(
                    icon = FeatherIcons.Archive,
                    labelBottom = "Square Btn",
                ),
                state = ButtonState.Loading
            )
            DsButton(
                text = "Moendy Pill",
                onClick = {},
                type = ButtonShape.Square(
                    labelBottom = "Square Btn",
                ),
                state = ButtonState.Enabled
            )
            Spacer(Modifier.padding(4.dp))
            DsButton(
                onClick = {},
                type = ButtonShape.Circle(
                    labelBottom = "Moendy Circle",
                    icon = FeatherIcons.Archive
                )
            )
            Spacer(Modifier.padding(4.dp))
            DsButton(
                type = ButtonShape.Circle(
                    icon = FeatherIcons.Archive,
                    labelBottom = "Moendy Circle",
                ),
                state = ButtonState.Disabled
            )
        }
    }
}