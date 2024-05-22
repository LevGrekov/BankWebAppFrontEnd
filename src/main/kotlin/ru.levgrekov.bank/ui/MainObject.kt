package ru.levgrekov.bank.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import ru.levgrekov.bank.ui.screens.auth.AuthViewModel
import ru.levgrekov.bank.ui.screens.main.MainViewModel
import ru.levgrekov.bank.ui.screens.product.ProductViewModel


object MainObject {

    var token: String? = null

    private val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    private val clientScope = CoroutineScope(Dispatchers.IO)


    suspend fun sendRequest(
        rout: String,
        httpMethod: HttpMethod,
        body: @Serializable Any? = null
    ): HttpResponse {
        return client.request {
            url {
                // Указываем URL и порт
                protocol = URLProtocol.HTTP
                host = "localhost"
                port = 8080
                appendPathSegments(rout)
            }
            // Устанавливаем HTTP-метод и тип контента
            method = httpMethod
            contentType(ContentType.Application.Json)
            // Устанавливаем тело запроса
            setBody(body)
            header(HttpHeaders.Authorization, "Bearer $token")
        }
    }

    var screenState by mutableStateOf(ScreenState.AUTH)

    val authViewModel = AuthViewModel()
    val mainViewModel = MainViewModel()
    val productViewModel = ProductViewModel()
    //val newProductViewModel = NewProductViewModel()

}

sealed class ScreenStateS {
    data object Auth : ScreenStateS()
    data object Main : ScreenStateS()
    data class Product(val id:Int) : ScreenStateS()
}

enum class ScreenState {
    MAIN,
    AUTH,
    PRODUCT,
}