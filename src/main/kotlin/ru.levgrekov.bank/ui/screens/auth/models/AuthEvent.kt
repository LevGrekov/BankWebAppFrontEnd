package ru.levgrekov.bank.ui.screens.auth.models

import java.time.LocalDate

sealed class AuthEvent {

    data class PhoneChanged(val phone: String) : AuthEvent()
    data class PasswordChanged(val password: String) : AuthEvent()
    data class FirstNameChanged(val firstname: String) : AuthEvent()
    data class SecondNameChanged(val secondName: String) : AuthEvent()
    data class ThirdNameChanged(val thirdName: String) : AuthEvent()
    data class BirthDateChanged(val birthDate: LocalDate) : AuthEvent()
    
    data object ModeChangeClick: AuthEvent()
    data object ExecuteButtonClick: AuthEvent()
}