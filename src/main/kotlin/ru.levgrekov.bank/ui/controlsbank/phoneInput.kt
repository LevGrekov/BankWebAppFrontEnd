package ru.levgrekov.bank.ui.controlsbank

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun phoneInput(
    modifier: Modifier = Modifier,
    phone: String,
    onLoginChanged: (login: String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = phone,
        onValueChange = {onLoginChanged(it)} ,
        label = {
            Text(text = "Номер Телефона")
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
        }
    )
}
