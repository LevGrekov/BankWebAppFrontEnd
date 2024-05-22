package ru.levgrekov.bank.ui.screens.main.models

sealed class MainScreenEvents {
    data class ClickNewProduct(val id: Int) : MainScreenEvents()
    data class ClickProduct(val id: Int) : MainScreenEvents()

    data object EnterScreen : MainScreenEvents()
    data object ShowProducts : MainScreenEvents()
    data object ClickHistory : MainScreenEvents()
    data class ClickGetNewProduct(val id: Int) : MainScreenEvents()

    data object MakeTransferClick : MainScreenEvents()
    data class AmountChanged(val value: String) : MainScreenEvents()
    data class RecipientChanged(val value: String) : MainScreenEvents()
}
