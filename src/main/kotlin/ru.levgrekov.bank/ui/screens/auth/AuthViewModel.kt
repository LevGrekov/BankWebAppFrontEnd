package ru.levgrekov.bank.ui.screens.auth


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.levgrekov.bank.logic.UserLogic
import ru.levgrekov.bank.ui.MainObject
import ru.levgrekov.bank.ui.MainObject.token
import ru.levgrekov.bank.ui.ViewModel
import ru.levgrekov.bank.ui.screens.auth.models.AuthEvent
import ru.levgrekov.bank.ui.screens.auth.models.AuthState



class AuthViewModel : ViewModel<AuthState, AuthEvent>(AuthState.Authorization()) {

    var isAuthenticate by mutableStateOf(false)

    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewState) {
            is AuthState.Registration -> reduce(viewEvent, viewState as AuthState.Registration)
            is AuthState.Authorization -> reduce(viewEvent, viewState as AuthState.Authorization)
        }
    }

    private fun reduce(event: AuthEvent, currState: AuthState.Authorization) {
        when (event) {
            AuthEvent.ModeChangeClick -> {
                viewState = AuthState.Registration()
            }

            AuthEvent.ExecuteButtonClick -> {
                authUser(currState)
            }

            is AuthEvent.PasswordChanged -> {
                viewState = currState.copy(
                    password = event.password,
                    enable = validatePhone(currState.phone) && validatePassword(event.password)
                )
            }

            is AuthEvent.PhoneChanged -> {
                viewState = currState.copy(
                    phone = event.phone,
                    enable = validatePhone(event.phone) && validatePassword(currState.password)
                )
            }

            else -> debugPrint(event)
        }

    }

    private fun reduce(event: AuthEvent, currState: AuthState.Registration) {
        when (event) {
            AuthEvent.ModeChangeClick -> {
                viewState = AuthState.Authorization()
            }

            AuthEvent.ExecuteButtonClick -> registerUser(currState)

            is AuthEvent.PasswordChanged -> {
                viewState = currState.copy(
                    password = event.password,
                    enable = validatePhone(currState.phone) && validatePassword(event.password)
                )
            }

            is AuthEvent.PhoneChanged -> {
                viewState = currState.copy(
                    phone = event.phone,
                    enable = validatePhone(event.phone) && validatePassword(currState.password)
                )
            }

            is AuthEvent.BirthDateChanged -> {
                viewState = currState.copy(birthdate = event.birthDate)
            }

            is AuthEvent.FirstNameChanged -> {
                viewState = currState.copy(firstname = event.firstname)
            }

            is AuthEvent.SecondNameChanged -> {
                viewState = currState.copy(secondName = event.secondName)
            }

            is AuthEvent.ThirdNameChanged -> {
                viewState = currState.copy(thirdName = event.thirdName)
            }
        }
    }

    private fun authUser(currState: AuthState.Authorization) {
        viewState = currState.copy(loading = true)
        viewModelScope.launch {

            val response = MainObject.sendRequest(
                "api/v1/login", HttpMethod.Post,
                UserLogic.LoginRequest(
                    phoneNumber = currState.phone,
                    password = currState.password
                )
            )

            withContext(this.coroutineContext) {
                if (response.status.isSuccess()) {
                    token = response.bodyAsText()
                    isAuthenticate = true
                } else {
                    viewState = AuthState.Authorization(
                        isError = true,
                        message = response.bodyAsText(),
                    )
                }
            }
        }
    }

    private fun registerUser(currState: AuthState.Registration) {
        viewState = currState.copy(loading = true)

        viewModelScope.launch {

            val response = MainObject.sendRequest(
                "api/v1/signup", HttpMethod.Post,
                UserLogic.RegisterRequest(
                    phoneNumber = currState.phone,
                    password = currState.password,
                    firstName = currState.firstname,
                    secondName = currState.secondName,
                    thirdName = currState.thirdName,
                    birthdate = currState.birthdate
                )
            )

            delay(1000)

            withContext(Dispatchers.Default) {
                viewState = if (response.status.isSuccess()) {
                    AuthState.Authorization(
                        isError = false,
                        message = response.bodyAsText(),
                    )
                } else {
                    currState.copy(
                        isError = true,
                        message = response.bodyAsText(),
                        loading = false,
                    )
                }
            }
        }
    }

    private fun validatePassword(password: String): Boolean = (password.length > 4)

    private fun validatePhone(phone: String): Boolean = (phone.length >= 10)


}