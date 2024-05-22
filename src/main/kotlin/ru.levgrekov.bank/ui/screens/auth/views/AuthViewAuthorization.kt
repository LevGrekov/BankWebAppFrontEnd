package ru.levgrekov.bank.ui.screens.auth.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.levgrekov.bank.ui.controlsbank.PasswordInput
import ru.levgrekov.bank.ui.controlsbank.phoneInput
import ru.levgrekov.bank.ui.screens.auth.models.AuthState

@Composable
fun AuthViewAuthorization(
    modifier: Modifier = Modifier,
    state: AuthState.Authorization,
    onPhoneChanged: (login: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onAuthStateChanged: () -> Unit,
    onAuthenticate: () -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Авторизация",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                phoneInput(
                    modifier = Modifier.fillMaxWidth(),
                    phone = state.phone ?: "",
                    onLoginChanged = onPhoneChanged
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordInput(
                    modifier = Modifier.fillMaxWidth(),
                    password = state.password ?: "",
                    onPasswordChanged = onPasswordChanged
                )
                Spacer(modifier = Modifier.height(12.dp))

                state.message?.let {
                    Text(it, color = if (state.isError) MaterialTheme.colors.error else MaterialTheme.colors.primary)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Button(
                    modifier = modifier,
                    onClick = { onAuthenticate() },
                    enabled = state.enable
                ) {
                    Text(
                        "Войти"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = onAuthStateChanged,
            modifier = Modifier.padding(top = 8.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Нет аккаунта? Зарегистрироваться")
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}