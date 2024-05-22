package ru.levgrekov.bank.ui.screens.main.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp

@Composable
fun MainViewLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource("Sigma.png"),
            contentDescription =  null,
            modifier = Modifier.size(300.dp)
        )
        // Крутилка
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center).size(300.dp),
            color = MaterialTheme.colors.primary,
            strokeWidth = 9.dp // Размер крутилки
        )
    }
}