@file:OptIn(ExperimentalMaterialApi::class)

package angu.labs.ds.components.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardOptions.Companion.Default
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import angu.labs.ds.components.artwork.ArtworkSize
import angu.labs.ds.components.input.TextFieldState.Disabled
import angu.labs.ds.components.input.TextFieldState.Loading
import angu.labs.ds.components.input.TextFieldState.ReadOnly
import angu.labs.ds.components.input.TextFieldState.Success
import angu.labs.ds.components.text.DsText
import angu.labs.ds.theme.DsTheme
import angu.labs.ds.tokens.color.DsColor
import angu.labs.ds.tokens.typography.DsTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun TextField(
    modifier: Modifier = Modifier,
    maxCount: Int? = null,
    placeHolder: String,
    text: String? = null,
    labelIcon: ImageVector? = null,
    label: String? = null,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    onEndIconClick: (() -> Unit)? = null,
    onStartIconClick: (() -> Unit)? = null,
    startLabel: String? = null,
    endLabel: String? = null,
    hint: String? = null,
    hintIcon: ImageVector? = null,
    isComplete: Boolean = false,
    isIncomplete: Boolean = false,
    maxLines: Int = 1,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    size: TextFieldSize = TextFieldSize.Medium,
    type: TextFieldType = TextFieldType.Default(),
    state: TextFieldState = TextFieldState.Active,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(DsTheme.spacers.space2x)
    ) {
        val textSanitized = text.orEmpty().take(maxCount ?: text.orEmpty().length)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        if (labelIcon != null || label != null || maxCount != null) {
            LabelContent(
                label = label.orEmpty(),
                icon = labelIcon,
                maxCount = maxCount,
                size = size,
                textLength = textSanitized.length,
                state = state
            )
        }
        BaseTextField(
            modifier = Modifier
                .clip(DsTheme.shapes.medium)
                .background(state.background())
                .border(
                    width = 3.dp,
                    color = state.borderColor(),
                    shape = DsTheme.shapes.medium
                )
                .padding(horizontal = DsTheme.spacers.space4x),
            leadingContent = {
                AnimatedVisibility(startIcon != null || startLabel != null) {
                    ArtworkContentStart(
                        size = size,
                        state = state,
                        onClick = onStartIconClick,
                        label = startLabel,
                        icon = startIcon
                    )
                }
            },
            textContent = {
                CompositionLocalProvider(
                    LocalTextSelectionColors provides TextSelectionColors(
                        handleColor = DsTheme.colors.contentPrimary,
                        backgroundColor = DsTheme.colors.contentInverseTertiary,
                    ),
                ) {
                    val textColor = with(DsTheme.colors) {
                        if (state == Disabled) contentStateDisabled else contentPrimary
                    }

                    BasicTextField(
                        modifier = Modifier.weight(1f).padding(
                            vertical = size.verticalPadding(),
                        ),
                        visualTransformation = visualTransformation,
                        value = textSanitized,
                        textStyle = size.placeHolderTypography().style.copy(color = textColor),
                        keyboardOptions = type.keyboardOptions,
                        onValueChange = {
                            val value = if (maxCount != null) it.take(maxCount) else it
                            onValueChange(value)
                        },
                        minLines = minLines,
                        enabled = state != Disabled && state != Loading && state != ReadOnly,
                        cursorBrush = SolidColor(DsTheme.colors.contentPrimary),
                        decorationBox = @Composable { innerTextField ->
                            TextFieldDefaults.TextFieldDecorationBox(
                                value = text.orEmpty(),
                                visualTransformation = VisualTransformation.None,
                                innerTextField = innerTextField,
                                placeholder = {
                                    DsText(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = placeHolder,
                                        typography = size.placeHolderTypography(),
                                        textAlign = TextAlign.Start,
                                        maxLines = maxLines,
                                        contentColor = with(DsTheme.colors) {
                                            if (state == Disabled) contentStateDisabled else contentTertiary
                                        }
                                    )
                                },
                                singleLine = maxLines == 1,
                                enabled = state != Disabled,
                                isError = state == TextFieldState.Error,
                                interactionSource = remember { MutableInteractionSource() },
                                contentPadding = PaddingValues(0.dp)
                            )
                        }
                    )
                }
            },
            trailingContent = {
                when {
                    isComplete -> {
                        Icon(
                            modifier = Modifier
                                .size(DsTheme.spacers.space4x)
                                .clip(CircleShape)
                                .background(DsTheme.colors.backgroundPositive)
                                .padding(2.dp),
                            imageVector = DsTheme.icons.check,
                            tint = DsColor.white,
                            contentDescription = null,
                        )
                    }

                    state == Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(DsTheme.spacers.space6x),
                            color = DsTheme.colors.borderAccent,
                            backgroundColor = DsColor.white,
                            strokeWidth = 2.5.dp
                        )
                    }

                    isIncomplete -> {
                        Box(
                            modifier = Modifier
                                .size(DsTheme.spacers.space4x)
                                .clip(CircleShape)
                                .background(DsTheme.colors.backgroundNegative),
                            contentAlignment = Alignment.Center
                        ) {
                            DsText(
                                text = "!",
                                textAlign = TextAlign.Center,
                                typography = DsTypography.Label.xSmall,
                                contentColor = DsColor.white
                            )
                        }
                    }

                    else -> AnimatedVisibility(endIcon != null || endLabel != null) {
                        ArtworkContentEnd(
                            size = size,
                            state = state,
                            onClick = onEndIconClick,
                            label = endLabel,
                            icon = endIcon
                        )
                    }
                }
            },
        )
        AnimatedVisibility(hint != null) {
            HintContent(hint = hint, icon = hintIcon, state = state)
        }
    }
}

public enum class TextFieldSize(
    public val iconSize: @Composable () -> Dp,
    public val verticalPadding: @Composable () -> Dp,
    public val labelTypography: @Composable () -> DsTypography,
    public val placeHolderTypography: @Composable () -> DsTypography,
    public val artworkSize: ArtworkSize,
) {
    Small(
        iconSize = { DsTheme.spacers.space4x },
        verticalPadding = { DsTheme.spacers.space2x },
        labelTypography = { DsTypography.Label.small },
        placeHolderTypography = { DsTypography.Paragraph.small },
        artworkSize = ArtworkSize.Small,

        ),
    Medium(
        iconSize = { DsTheme.spacers.space5x },
        verticalPadding = { DsTheme.spacers.space3x },
        labelTypography = { DsTypography.Label.medium },
        placeHolderTypography = { DsTypography.Paragraph.medium },
        artworkSize = ArtworkSize.Medium,
    ),
    Large(
        iconSize = { DsTheme.spacers.space6x },
        verticalPadding = { DsTheme.spacers.space4x },
        labelTypography = { DsTypography.Label.large },
        placeHolderTypography = { DsTypography.Paragraph.large },
        artworkSize = ArtworkSize.Medium,
    )
}

/**
 * @See https://base.uber.com/6d2425e9f/p/51073e-text-field/t/21dc3e
 */
public sealed class TextFieldType(
    public open val keyboardOptions: KeyboardOptions = Default,
) {
    public data class Default(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)

    public data class Search(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)

    public data class Pin(
        override val keyboardOptions: KeyboardOptions = Default,
        val length: Int = 4
    ) : TextFieldType(keyboardOptions)

    public data class Password(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)

    public data class Phone(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)

    public data class Stepper(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)

    public data class TextArea(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)

    public data class FieldArea(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)

    public data class PaymentCard(
        override val keyboardOptions: KeyboardOptions = Default,
    ) : TextFieldType(keyboardOptions)
}

public enum class TextFieldState(
    public val borderColor: @Composable () -> Color,
    public val background: @Composable () -> Color,
    // artwork colors
    public val iconTint: @Composable () -> Color,
    public val backgroundIcon: @Composable () -> Color,
    // hint
    public val hintTextColor: @Composable () -> Color,
    public val backgroundHintIcon: @Composable () -> Color = { DsTheme.colors.backgroundPrimary },
    public val tintHintIcon: @Composable () -> Color = { DsTheme.colors.contentPrimary },

    ) {
    Active(
        borderColor = { DsTheme.colors.borderSelected },
        hintTextColor = { DsTheme.colors.contentTertiary },
        background = { DsTheme.colors.backgroundPrimary },
        backgroundIcon = { DsTheme.colors.backgroundPrimary },
        iconTint = { DsTheme.colors.contentPrimary },
    ),
    Enabled(
        borderColor = { DsTheme.colors.backgroundTertiary },
        hintTextColor = { DsTheme.colors.contentTertiary },
        background = { DsTheme.colors.backgroundTertiary },
        backgroundIcon = { DsTheme.colors.backgroundTertiary },
        iconTint = { DsTheme.colors.contentPrimary },
    ),
    Error(
        borderColor = { DsTheme.colors.borderNegative },
        hintTextColor = { DsTheme.colors.contentNegative },
        background = { DsTheme.colors.backgroundPrimary },
        backgroundIcon = { DsTheme.colors.backgroundPrimary },
        iconTint = { DsTheme.colors.contentPrimary },
        backgroundHintIcon = { DsTheme.colors.backgroundNegative },
        tintHintIcon = { DsColor.white }
    ),
    Disabled(
        borderColor = { DsTheme.colors.backgroundStateDisabled },
        hintTextColor = { DsTheme.colors.contentStateDisabled },
        background = { DsTheme.colors.backgroundStateDisabled },
        backgroundIcon = { DsTheme.colors.backgroundStateDisabled },
        iconTint = { DsTheme.colors.contentStateDisabled },
        tintHintIcon = { DsTheme.colors.contentStateDisabled }
    ),
    Success(
        borderColor = { DsTheme.colors.borderPositive },
        hintTextColor = { DsTheme.colors.contentPositive },
        background = { DsTheme.colors.backgroundPrimary },
        backgroundIcon = { DsTheme.colors.backgroundPrimary },
        iconTint = { DsTheme.colors.contentPrimary },
        backgroundHintIcon = { DsTheme.colors.backgroundPositive },
        tintHintIcon = { DsColor.white }
    ),

    Loading(
        borderColor = { DsTheme.colors.backgroundTertiary },
        hintTextColor = { DsTheme.colors.contentTertiary },
        background = { DsTheme.colors.backgroundTertiary },
        backgroundIcon = { DsTheme.colors.backgroundTertiary },
        iconTint = { DsTheme.colors.contentPrimary },
    ),

    ReadOnly(
        borderColor = { DsTheme.colors.borderOpaque },
        hintTextColor = { DsTheme.colors.contentTertiary },
        background = { DsTheme.colors.backgroundPrimary },
        backgroundIcon = { DsTheme.colors.backgroundPrimary },
        iconTint = { DsTheme.colors.contentPrimary },
    )
}

@Composable
private fun BaseTextField(
    modifier: Modifier = Modifier,
    leadingContent: @Composable (() -> Unit)? = null,
    textContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    var hasFocus by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space4x)
    ) {
        leadingContent?.let { it() }
        textContent?.let { it() }
        trailingContent?.let { it() }
    }
}

@Composable
private fun LabelContent(
    label: String? = null,
    maxCount: Int? = null,
    textLength: Int? = null,
    icon: ImageVector? = null,
    size: TextFieldSize = TextFieldSize.Medium,
    state: TextFieldState,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space2x)
    ) {

        if (label != null) {
            DsText(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                text = label,
                typography = size.labelTypography(),
                textAlign = TextAlign.Start,
                maxLines = 1,
                contentColor = with(DsTheme.colors) {
                    if (state == Disabled) contentStateDisabled else contentPrimary
                }
            )
        }

        if (maxCount != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space2x)
            ) {
                if (icon != null) {
                    Icon(
                        modifier = Modifier.size(14.dp),
                        imageVector = icon,
                        contentDescription = null,
                        tint = with(DsTheme.colors) {
                            if (state == Disabled) contentStateDisabled else contentPrimary
                        },
                    )
                }
                DsText(
                    text = "${textLength ?: 0}/$maxCount",
                    typography = size.labelTypography(),
                    textAlign = TextAlign.End,
                    contentColor = with(DsTheme.colors) {
                        if (state == Disabled) contentStateDisabled else contentTertiary
                    },
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun HintContent(
    hint: String? = null,
    icon: ImageVector? = null,
    state: TextFieldState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space1x)
    ) {
        val paddingIcon = if (state == TextFieldState.Error || state == Success) 2.dp else 0.dp
        if (icon != null) {
            Icon(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(state.backgroundHintIcon())
                    .padding(paddingIcon),
                imageVector = icon,
                tint = state.tintHintIcon(),
                contentDescription = null
            )
        }
        DsText(
            text = hint.orEmpty(),
            typography = DsTypography.Paragraph.small,
            textAlign = TextAlign.Start,
            contentColor = state.hintTextColor(),
            maxLines = 1
        )
    }
}

@Composable
private fun ArtworkContentStart(
    size: TextFieldSize = TextFieldSize.Medium,
    state: TextFieldState,
    onClick: (() -> Unit)? = null,
    label: String? = null,
    icon: ImageVector? = null
) {
    Row(
        modifier = Modifier.then(onClick?.let { Modifier.clickable { it() } } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space4x)
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier
                    .size(size.artworkSize.iconSize)
                    .clip(CircleShape)
                    .background(state.backgroundIcon()),
                imageVector = icon,
                tint = state.iconTint(),
                contentDescription = null
            )
        }
        if (label != null) {
            DsText(
                text = label,
                typography = size.labelTypography(),
                contentColor = with(DsTheme.colors) {
                    if (state == Disabled) contentStateDisabled else contentPrimary
                }
            )
        }
    }
}

@Composable
private fun ArtworkContentEnd(
    size: TextFieldSize = TextFieldSize.Medium,
    state: TextFieldState,
    onClick: (() -> Unit)? = null,
    label: String? = null,
    icon: ImageVector? = null
) {
    Row(
        modifier = Modifier.then(onClick?.let { Modifier.clickable { it() } } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DsTheme.spacers.space4x)
    ) {

        if (label != null) {
            DsText(
                text = label,
                typography = size.labelTypography(),
                contentColor = with(DsTheme.colors) {
                    if (state == Disabled) contentStateDisabled else contentPrimary
                }
            )
        }

        if (icon != null) {
            Icon(
                modifier = Modifier
                    .size(size.artworkSize.iconSize)
                    .clip(CircleShape)
                    .background(state.backgroundIcon()),
                imageVector = icon,
                tint = state.iconTint(),
                contentDescription = null
            )
        }
    }
}