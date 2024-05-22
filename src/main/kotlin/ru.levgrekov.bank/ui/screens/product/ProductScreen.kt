package ru.levgrekov.bank.ui.screens.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.levgrekov.bank.ui.screens.main.views.MainViewLoading
import ru.levgrekov.bank.ui.screens.product.models.ProductEvents
import ru.levgrekov.bank.ui.screens.product.models.ProductState
import ru.levgrekov.bank.ui.screens.product.views.ProductDisplayView

@Composable
fun ProductScreen(vm: ProductViewModel) {
    val viewState by vm.viewStates().collectAsState()

    when (val state = viewState) {
        is ProductState.Loading -> MainViewLoading()
        is ProductState.Display -> ProductDisplayView(
            state = state,
            onChangeRecipient = { vm.obtainEvent(ProductEvents.RecipientChanged(it)) },
            onChangeAmount = { vm.obtainEvent(ProductEvents.AmountChanged(it)) },
            onMakeTransaction = { vm.obtainEvent(ProductEvents.MakeTransferClick) },
            onFloatingButtonClick = { vm.obtainEvent(ProductEvents.FloatingButtonClick) }
        )
    }
}