package org.mahd_e_learning_platform.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class Shapes(
    val large: Shape,
    val medium: Shape,
    val small: Shape,
)

val LocalShapes =
    staticCompositionLocalOf {
        Shapes(
            large = RoundedCornerShape(24.dp),
            medium = RoundedCornerShape(16.dp),
            small = RoundedCornerShape(8.dp)
        )
    }