package ru.levgrekov.bank.ui.screens.auth.models

import java.time.LocalDate

sealed class AuthState {

    data class Registration(
        val firstname: String = "",
        val secondName: String = "",
        val thirdName: String = "",
        val birthdate: LocalDate = LocalDate.now(),
        val loading: Boolean = false,

        val phone: String = "",
        val password: String = "",
        val message: String? = null,
        val isError: Boolean = true,
        val enable: Boolean = false,

    ) : AuthState()

    data class Authorization(
        val phone: String = "",
        val password: String = "",
        val message: String? = null,
        val isError: Boolean = true,
        val enable: Boolean = false,
        val loading: Boolean = false,
    ) : AuthState()
}

//var phone: String by mutableStateOf("")
//var password: String by mutableStateOf("")
//var firstname: String by mutableStateOf("")
//var secondName: String by mutableStateOf("")
//var thirdName: String? by mutableStateOf(null)
//var birthdate: LocalDate by mutableStateOf(LocalDate.now())
//var registering by mutableStateOf(false)