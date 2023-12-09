package di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin {
    modules(networkModule)
}

private val networkModule = module {
    single {
        HttpClient {
            defaultRequest {
                url.takeFrom(URLBuilder().takeFrom("https://internshala.com/"))
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
            }
//            install(ContentNegotiation) {
//                json(Json {
//                    ignoreUnknownKeys = true
//                    prettyPrint = true
//                })
//            }
//            install(Logging) {
//                level = LogLevel.ALL
//                logger = object : Logger {
//                    override fun log(message: String) {
//                        println(message)
//                    }
//                }
//            }
        }
    }
}