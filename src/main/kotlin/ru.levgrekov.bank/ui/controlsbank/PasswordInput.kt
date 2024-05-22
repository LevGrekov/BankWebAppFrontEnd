package ru.levgrekov.bank.ui.controlsbank

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.EyeSlashSolid
import compose.icons.lineawesomeicons.EyeSolid

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String?,
    onPasswordChanged: (password: String) -> Unit
) {
    var isPasswordHidden by remember { mutableStateOf(false) }
    TextField(
        modifier = modifier,
        value = password ?: "",
        singleLine = true,
        onValueChange = {
            onPasswordChanged(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                isPasswordHidden = !isPasswordHidden
            }){
                Icon(
                    imageVector = if (isPasswordHidden) {
                        LineAwesomeIcons.EyeSlashSolid
                    } else LineAwesomeIcons.EyeSolid,
                    contentDescription = null
                )
            }
        },
        label = {
            Text(text = "Пароль")
        },
        visualTransformation = if(isPasswordHidden) VisualTransformation.None else PasswordVisualTransformation()
    )
}