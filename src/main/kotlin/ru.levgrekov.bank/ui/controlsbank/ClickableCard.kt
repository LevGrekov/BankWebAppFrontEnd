package ru.levgrekov.bank.ui.controlsbank

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ClickableCard(modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable () -> Unit) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp), // Форма карточки
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}