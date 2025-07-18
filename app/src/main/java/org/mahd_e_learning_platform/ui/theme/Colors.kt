package org.mahd_e_learning_platform.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Colors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val onBackground: Color,
    val bottomSheetColor: Color,
    val bottomIconColor: Color,
    val bottomIconActiveColor: Color,
    val text: Color,
    val subText: Color,
    val error: Color,
    val white: Color,
    val black: Color,
    val textFieldIndicatorColor: Color,
    val purple: Color,
    val blue: Color,
    val green: Color,
    val lightRed: Color,
    val yalow: Color,
    )
val LOCAL_COLORS = staticCompositionLocalOf {
    Colors(
        primary = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        bottomSheetColor = Color.Unspecified,
        bottomIconColor = Color.Unspecified,
        bottomIconActiveColor = Color.Unspecified,
        text = Color.Unspecified,
        subText = Color.Unspecified,
        error = Color.Unspecified,
        secondary = Color.Unspecified,
        white = Color.Unspecified,
        black = Color.Unspecified,
        textFieldIndicatorColor = Color.Unspecified,
        purple = Color.Unspecified,
        blue = Color.Unspecified,
        lightRed = Color.Unspecified,
        green = Color.Unspecified,
        yalow = Color.Unspecified,
    )
}