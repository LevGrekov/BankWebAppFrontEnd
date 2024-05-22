package ru.levgrekov.bank.net

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable


object Client {

    var token: String? = null

    private val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

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
}