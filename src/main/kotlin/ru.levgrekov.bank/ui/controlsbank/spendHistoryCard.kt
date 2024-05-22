package ru.levgrekov.bank.ui.controlsbank

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun spendHistoryCard(modifier: Modifier = Modifier,onClick: () -> Unit, title: String,subtitle: String,amount: String){
    ClickableCard(
        modifier = modifier,
        onClick = onClick
    ){
        Column {
            Text(
                text = title,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = subtitle ,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "$amount ₽",
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}