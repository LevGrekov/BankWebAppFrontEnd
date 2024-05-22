package ru.levgrekov.bank.logic

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
sealed interface TransferLogic {
    data class FullTransferLogic(
        val id: Int,
        val senderAccountId: Int,
        val receiverAccountId: Int,
        val amount: Double,
        val timestamp: LocalDate,
        val transactionType: Int
    )
}