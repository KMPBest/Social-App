package org.edward.app.di

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            expectSuccess = true
            defaultRequest {
                url.takeFrom(URLBuilder().takeFrom("https://fakestoreapi.com/"))
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d("--------------------------------------------------")
                        Napier.d("[HttpClient] $message")
                    }
                }
            }
            install(Auth) {
                bearer {

                }
            }
            HttpResponseValidator {
                validateResponse { response ->
                    when (response.status.value) {
                        in 400..499 -> throw ClientRequestException(response, "Client error")
                        in 500..599 -> throw ServerResponseException(response, "Server error")
                    }
                }
            }
        }
    }
}