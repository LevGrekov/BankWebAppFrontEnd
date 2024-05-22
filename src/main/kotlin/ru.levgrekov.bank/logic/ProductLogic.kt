package ru.levgrekov.bank.logic

import kotlinx.serialization.Serializable
import ru.levgrekov.bank.logic.serialization.LocalDateSerializer
import java.time.LocalDate

@Serializable
sealed interface ProductLogic {
    val id: Int

    @Serializable
    data class Full(
        override val id: Int,
        val name: String,
        val clientId: Int,
        val type: String,
        val balance: Double,
        @Serializable(with = LocalDateSerializer::class)
        val openingDate: LocalDate,
        @Serializable(with = LocalDateSerializer::class)
        val expirationDate: LocalDate?
    ) : ProductLogic

    @Serializable
    data class Short(
        override val id: Int,
        val name: String,
        val type: String,
        val balance: Double,
    ) : ProductLogic
}