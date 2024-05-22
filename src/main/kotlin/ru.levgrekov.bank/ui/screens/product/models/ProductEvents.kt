package ru.levgrekov.bank.ui.screens.product.models

sealed class ProductEvents {
    data class AmountChanged(val value:String) : ProductEvents()
    data class RecipientChanged(val value:String) : ProductEvents()
    data object FloatingButtonClick: ProductEvents()
    data object MakeTransferClick: ProductEvents()
}