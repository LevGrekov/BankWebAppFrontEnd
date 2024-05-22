package ru.levgrekov.bank.ui.screens.main.models

import ru.levgrekov.bank.logic.AvailableProductLogic
import ru.levgrekov.bank.logic.ProductLogic


sealed class MainScreenState {

    data object Loading : MainScreenState()

    data class NewProduct(val product: AvailableProductLogic.Full) : MainScreenState()

    data class Display(
        val userName: String,
        val productsOpen: Boolean,
        val userExpenses: String,
        val availableProductsList: List<AvailableProductLogic.Short>,
        val userProductsList: List<ProductLogic>
    ) : MainScreenState()
}

