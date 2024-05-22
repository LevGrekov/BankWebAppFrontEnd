package ru.levgrekov.bank.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = Orange,
            primaryVariant = Orange2,
            secondary = Color(0xFF4CAF50),
        ),
        typography = Typography(
            defaultFontFamily = FontFamily.Serif,
            h1 = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF2196F3)
            )
        ),
        content = content
    )
}