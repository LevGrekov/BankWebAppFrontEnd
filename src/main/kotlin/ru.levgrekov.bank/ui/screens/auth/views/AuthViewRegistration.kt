package ru.levgrekov.bank.ui.screens.auth.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.levgrekov.bank.ui.controlsbank.DatePickerInput
import ru.levgrekov.bank.ui.controlsbank.PasswordInput
import ru.levgrekov.bank.ui.controlsbank.phoneInput
import ru.levgrekov.bank.ui.screens.auth.models.AuthState
import java.time.LocalDate

@Composable
fun AuthViewRegistration(
    modifier: Modifier = Modifier,
    state: AuthState.Registration,
    onPhoneChanged: (login: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onFirstNameChanged: (firstname: String) -> Unit,
    onSecondNameChanged: (secondName: String) -> Unit,
    onThirdNameChanged: (thirdName: String) -> Unit,
    onDateChanged: (birthdate: LocalDate) -> Unit,
    onRegister: () -> Unit,
    onAuthStateChanged: () -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Регистрация",
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

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.firstname ?: "",
                    singleLine = true,
                    onValueChange = { onFirstNameChanged(it) },
                    label = { Text(text = "Имя") },
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.secondName ?: "",
                    singleLine = true,
                    onValueChange = { onSecondNameChanged(it) },
                    label = { Text(text = "Фамилия") },
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.thirdName ?: "",
                    singleLine = true,
                    onValueChange = { onThirdNameChanged(it) },
                    label = { Text(text = "Отчество") },
                )
                Spacer(modifier = Modifier.height(12.dp))
                DatePickerInput(
                    modifier = Modifier.fillMaxWidth(),
                    localDate = state.birthdate,
                    onDateChanged = { onDateChanged(it) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                state.message?.let {
                    Text(it, color = if (state.isError) MaterialTheme.colors.error else MaterialTheme.colors.primary)
                    Spacer(modifier = Modifier.height(12.dp))
                }
                if(state.loading){
                    CircularProgressIndicator()
                } else {
                    Button(
                        modifier = modifier,
                        onClick = onRegister ,
                        enabled = state.enable
                    ) {
                        Text(text ="Зарегистрироваться")
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextButton(
            onClick = onAuthStateChanged,
            modifier = Modifier.padding(top = 8.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Уже есть аккаунт? Войти")
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}