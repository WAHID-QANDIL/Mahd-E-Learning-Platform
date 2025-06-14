package org.mahd_e_learning_platform.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(
    val titleVeryLarge: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val headlineMedium: TextStyle,
) {

}

val LOCAL_TYPOGRAPHY = staticCompositionLocalOf {
    Typography(
        titleVeryLarge = TextStyle.Default,
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleSmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodySmall = TextStyle.Default,
        headlineMedium = TextStyle(
            fontFamily = FontFamily.Default, // Replace with your custom font if needed
            fontWeight = FontWeight.SemiBold, // Or your desired weight
            fontSize = 28.sp, // Or your desired size
            lineHeight = 36.sp, // Or your desired line height
            letterSpacing = 0.sp // Or your desired letter spacing
        ),
    )
}