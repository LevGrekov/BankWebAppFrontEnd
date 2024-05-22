package ru.levgrekov.bank.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.levgrekov.bank.ui.screens.auth.models.AuthEvent
import ru.levgrekov.bank.ui.screens.auth.models.AuthState
import ru.levgrekov.bank.ui.screens.auth.views.AuthViewAuthorization
import ru.levgrekov.bank.ui.screens.auth.views.AuthViewRegistration

@Composable
fun AuthScreen(
    vm: AuthViewModel
) {
    val viewState by vm.viewStates().collectAsState()

    when (val state = viewState) {
        is AuthState.Registration -> {
            AuthViewRegistration(
                modifier = Modifier,
                state = state,
                onPhoneChanged = { vm.obtainEvent(AuthEvent.PhoneChanged(it)) },
                onPasswordChanged = { vm.obtainEvent(AuthEvent.PasswordChanged(it)) },
                onFirstNameChanged = { vm.obtainEvent(AuthEvent.FirstNameChanged(it)) },
                onSecondNameChanged = { vm.obtainEvent(AuthEvent.SecondNameChanged(it)) },
                onThirdNameChanged = { vm.obtainEvent(AuthEvent.ThirdNameChanged(it)) },
                onDateChanged = { vm.obtainEvent(AuthEvent.BirthDateChanged(it)) },
                onRegister = { vm.obtainEvent(AuthEvent.ExecuteButtonClick) },
                onAuthStateChanged = { vm.obtainEvent(AuthEvent.ModeChangeClick) },
            )
        }

        is AuthState.Authorization -> {
            AuthViewAuthorization(
                modifier = Modifier,
                state = state,
                onPhoneChanged = { vm.obtainEvent(AuthEvent.PhoneChanged(it)) },
                onPasswordChanged = { vm.obtainEvent(AuthEvent.PasswordChanged(it)) },
                onAuthStateChanged = { vm.obtainEvent(AuthEvent.ModeChangeClick) },
                onAuthenticate = { vm.obtainEvent(AuthEvent.ExecuteButtonClick) },
            )
        }
    }
}