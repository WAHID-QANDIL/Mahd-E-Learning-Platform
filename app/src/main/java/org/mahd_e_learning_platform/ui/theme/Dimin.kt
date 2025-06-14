package org.mahd_e_learning_platform.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimin(
    val smallPadding: Dp,
    val extraSmallPadding: Dp,
    val mediumPadding: Dp,
    val largePadding: Dp,
    val smallFraction: Float,
    val mediumFraction: Float,
    val largeFraction: Float,
)

val LOCAL_DIMIN = staticCompositionLocalOf {
    Dimin(
        extraSmallPadding = 32.dp,
        smallPadding = 0.dp,
        mediumPadding = 0.dp,
        largePadding = 64.dp,
        smallFraction = 0f,
        mediumFraction = 0f,
        largeFraction = 0f,
    )
}