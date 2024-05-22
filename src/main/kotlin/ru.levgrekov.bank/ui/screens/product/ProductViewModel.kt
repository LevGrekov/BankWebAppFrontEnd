package ru.levgrekov.bank.ui.screens.product

import ru.levgrekov.bank.ui.ViewModel
import ru.levgrekov.bank.ui.screens.product.models.ProductEvents
import ru.levgrekov.bank.ui.screens.product.models.ProductState

class ProductViewModel : ViewModel<ProductState,ProductEvents>(ProductState.Loading) {
    override fun obtainEvent(viewEvent: ProductEvents) {
        TODO("Not yet implemented")
    }
}