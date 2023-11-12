package angu.labs.sample

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

internal object ThemeState {
    public var darkModeState: MutableState<Boolean> = mutableStateOf(false)
}