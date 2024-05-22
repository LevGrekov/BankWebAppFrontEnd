package ru.levgrekov.bank.logic

import kotlinx.serialization.Serializable
import ru.levgrekov.bank.ui.LocalDateSerializer
import java.time.LocalDate

@Serializable
sealed interface UserLogic {
    val phoneNumber: String
    val password: String

    @Serializable
    data class FullUserLogic(
        val id: Int,
        override val phoneNumber: String,
        override val password: String,
        val firstName: String,
        val secondName: String,
        val thirdName: String?,
        @Serializable(with = LocalDateSerializer::class)
        val birthdate: LocalDate?,
        @Serializable(with = LocalDateSerializer::class)
        val regDate: LocalDate
    ) : UserLogic

    @Serializable
    data class RegisterRequest(
        override val phoneNumber: String,
        override val password: String,
        val firstName: String,
        val secondName: String,
        val thirdName: String?,
        @Serializable(with = LocalDateSerializer::class)
        val birthdate: LocalDate?,
    ) : UserLogic

    @Serializable
    data class LoginRequest(
        override val phoneNumber: String,
        override val password: String
    ) : UserLogic
}



