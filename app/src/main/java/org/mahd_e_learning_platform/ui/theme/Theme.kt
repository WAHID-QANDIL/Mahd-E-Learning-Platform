package org.mahd_e_learning_platform.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MahdELearningPlatformTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val colors = Colors(
        primary = Color(0xFF01FE84),
        secondary = Color(0xFF01F37E),
        background = if (isDark) Color(0xFF111827) else Color(0XFFFFFFFF),
        onBackground = Color(0XFF0296E5),
        bottomSheetColor = if (isDark) Color(0xFF1E1E1E) else Color(0XFFFFFFFF),
        bottomIconColor = Color(0XFF67686D),
        bottomIconActiveColor = Color(0XFF0296E5),
        text = Color(0XFFFFFFFF),
        subText = Color(0XFF67686D),
        error = Color(0xFFFF0000),
        white = Color(0XFFFFFFFF),
        black = if (!isSystemInDarkTheme()) Color(0xFF000000) else Color(0XFFFFFFFF) ,
        textFieldIndicatorColor = Color(0xFFA8A6A6),
        purple = Color(0xFF9333EA),
        blue = Color(0xFF2563EB),
        green = Color(0xFF16A34A),
        lightRed = Color(0xFFEA580C),
        yalow = Color(0xFFFACC15),


    )

    val typography = Typography(
        titleVeryLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                lineHeight = 21.09.sp
            ),
        titleLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                fontSize = 22.sp,
                lineHeight = 18.75.sp
            ),
        titleMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                fontSize = 14.sp,
                lineHeight = 16.41.sp
            ),
        titleSmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                fontSize = 12.sp,
                lineHeight = 14.06.sp
            ),
        bodyLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                lineHeight = 18.75.sp
            ),
        bodyMedium =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 16.41.sp
            ),
        bodySmall =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 14.06.sp
            ),
        headlineMedium =
            TextStyle(
                fontFamily = FontFamily.Default, // Replace with your custom font if needed
                fontWeight = FontWeight.SemiBold, // Or your desired weight
                fontSize = 28.sp, // Or your desired size
                lineHeight = 36.sp, // Or your desired line height
                letterSpacing = 0.sp // Or your desired letter spacing
    )
    )

    val shapes =
        Shapes(
            large = RoundedCornerShape(24.dp),
            medium = RoundedCornerShape(16.dp),
            small = RoundedCornerShape(8.dp)
        )
    val dimin = Dimin(
        extraSmallPadding = 8.dp,
        smallPadding = 8.dp,
        mediumPadding = 16.dp,
        largePadding = 24.dp,
        smallFraction = .3f,
        mediumFraction = .5f,
        largeFraction = .7f
    )

    CompositionLocalProvider(
        LOCAL_COLORS provides colors,
        LocalShapes provides shapes,
        LOCAL_TYPOGRAPHY provides typography,
        LOCAL_DIMIN provides dimin,
        content = content
    )


}
object MahdELearningPlatformTheme {
    val colors: Colors
        @Composable
        get() = LOCAL_COLORS.current
    val shapes: Shapes
        @Composable
        get() = LocalShapes.current
    val typography: Typography
        @Composable
        get() = LOCAL_TYPOGRAPHY.current
    val dimin: Dimin
        @Composable
        get() = LOCAL_DIMIN.current
}