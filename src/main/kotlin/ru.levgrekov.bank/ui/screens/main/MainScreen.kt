package ru.levgrekov.bank.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.levgrekov.bank.navigation.NavController
import ru.levgrekov.bank.ui.screens.main.models.MainScreenEvents
import ru.levgrekov.bank.ui.screens.main.models.MainScreenState
import ru.levgrekov.bank.ui.screens.main.views.MainViewDisplay
import ru.levgrekov.bank.ui.screens.main.views.MainViewLoading
import ru.levgrekov.bank.ui.screens.main.views.MainViewNewProduct
import ru.levgrekov.bank.ui.screens.main.views.ProductDisplayView


@Composable
fun MainScreen(
    vm: MainViewModel,
    navController: NavController
) {
    val viewState by vm.viewStates().collectAsState()

    when (val state = viewState) {
        is MainScreenState.Loading -> MainViewLoading()

        is MainScreenState.Display -> MainViewDisplay(
            state = state,
            onShowAllProducts = {vm.obtainEvent(MainScreenEvents.ShowProducts)},
            onOpenHistory = {vm.obtainEvent(MainScreenEvents.ClickHistory)},
            onProductClick = {vm.obtainEvent(MainScreenEvents.ClickNewProduct(it))}
        )

        is MainScreenState.NewProduct -> {
            MainViewNewProduct(
                state = state,
                onGetNewProduct = {vm.obtainEvent(MainScreenEvents.ClickGetNewProduct(it))},
                onFloatingButtonClick = {vm.obtainEvent(MainScreenEvents.EnterScreen)}
            )
        }

        is MainScreenState.Product -> ProductDisplayView(
            state = state,
            onChangeRecipient = { vm.obtainEvent(MainScreenEvents.RecipientChanged(it)) },
            onChangeAmount = { vm.obtainEvent(MainScreenEvents.AmountChanged(it)) },
            onMakeTransaction = { vm.obtainEvent(MainScreenEvents.MakeTransferClick) },
            onFloatingButtonClick = { vm.obtainEvent(MainScreenEvents.EnterScreen) }
        )
    }

    LaunchedEffect(key1 = Unit, block = {
        vm.obtainEvent(viewEvent = MainScreenEvents.EnterScreen)
    })
}