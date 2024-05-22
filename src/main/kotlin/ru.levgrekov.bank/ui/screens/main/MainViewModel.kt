package ru.levgrekov.bank.ui.screens.main

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import ru.levgrekov.bank.logic.AvailableProductLogic
import ru.levgrekov.bank.logic.ProductLogic
import ru.levgrekov.bank.net.Client.sendRequest
import ru.levgrekov.bank.ui.ViewModel
import ru.levgrekov.bank.ui.screens.main.models.MainScreenEvents
import ru.levgrekov.bank.ui.screens.main.models.MainScreenState

class MainViewModel : ViewModel<MainScreenState, MainScreenEvents>(MainScreenState.Loading) {


    override fun obtainEvent(viewEvent: MainScreenEvents) {
        when (viewState) {
            MainScreenState.Loading -> reduce(viewEvent, viewState as MainScreenState.Loading)
            is MainScreenState.Display -> reduce(viewEvent, viewState as MainScreenState.Display)
            is MainScreenState.NewProduct -> reduce(viewEvent, viewState as MainScreenState.NewProduct)
            is MainScreenState.Product -> reduce(viewEvent, viewState as MainScreenState.Product)
        }
    }

    private fun reduce(event: MainScreenEvents, currState: MainScreenState.Product) {

    }

    private fun reduce(event: MainScreenEvents, currState: MainScreenState.Loading) {
        when (event) {

            MainScreenEvents.EnterScreen -> {
                viewModelScope.launch {
                    delay(3000)
                    fetchMainScreenData()
                }
            }

            else -> debugPrint(event)
        }
    }

    private fun reduce(event: MainScreenEvents, currState: MainScreenState.NewProduct) {
        when (event) {
            MainScreenEvents.EnterScreen -> fetchMainScreenData()
            MainScreenEvents.ClickNewProduct(currState.product.id) -> performGetNewProductClick(currState.product.id)
            else -> debugPrint(event)
        }
    }

    private fun reduce(event: MainScreenEvents, currState: MainScreenState.Display) {
        when (event) {
            is MainScreenEvents.ClickProduct -> openExistProductView()

            is MainScreenEvents.ShowProducts -> showProducts(currState)

            is MainScreenEvents.ClickNewProduct -> openProductView(currState, event)

            else -> debugPrint(event)
        }
    }

    private fun performGetNewProductClick(productID: Int) {
        viewModelScope.launch {
            val productsResponse = async { sendRequest("api/v1/new_product", HttpMethod.Post, productID) }.await()
        }

    }


    private fun fetchMainScreenData() {
        viewState = MainScreenState.Loading
        viewModelScope.launch {
            val availableProductsResponse = async { sendRequest("api/v1/available_products", HttpMethod.Get) }.await()
            val availableProducts = availableProductsResponse.body<List<AvailableProductLogic.Short>>()


            val productsResponse = async { sendRequest("api/v1/products", HttpMethod.Get) }.await()
            val products = productsResponse.body<List<ProductLogic.Short>>()

            val userName = async { sendRequest("api/v1/username", HttpMethod.Get) }.await()
            if (productsResponse.status == HttpStatusCode.OK) {

                withContext(Dispatchers.Default) {
                    viewState = MainScreenState.Display(
                        userName.bodyAsText(),
                        false,
                        "300.0",
                        availableProducts,
                        products
                    )
                }
            }
        }
    }

    private fun showProducts(currState: MainScreenState.Display) {
        viewState = currState.copy(productsOpen = !currState.productsOpen)
    }

    private fun openExistProductView() {

    }

    private fun openProductView(currState: MainScreenState.Display, event: MainScreenEvents.ClickNewProduct) {
        viewState = MainScreenState.Loading
        viewModelScope.launch {
            val availableProductResponse =
                async { sendRequest("api/v1/available_products/${event.id}", HttpMethod.Get) }.await()
            val availableProduct = availableProductResponse.body<AvailableProductLogic.Full>()
            viewState = MainScreenState.NewProduct(availableProduct)
        }
    }
}