package ru.levgrekov.bank.ui.screens.product.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ru.levgrekov.bank.logic.ProductLogic

sealed class ProductState {
    data class Display(
        val product: ProductLogic.Full,
        var amountToTransfer: String,
        var recipientAccount: String,
    ) : ProductState()

    data object Loading: ProductState()
}