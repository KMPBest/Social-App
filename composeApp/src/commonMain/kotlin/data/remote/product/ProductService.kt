@file:OptIn(InternalAPI::class)

package data.remote.product

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.util.InternalAPI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.AsyncResult

class ProductService : KoinComponent {
    private val httpClient by inject<HttpClient>()

    companion object {
        const val PRODUCT = "products"
    }


    suspend fun getProducts(): AsyncResult<List<Product>> {
        return try {
            AsyncResult.Success(httpClient.get(PRODUCT).body<List<Product>>())
        } catch (e: Exception) {
            AsyncResult.Error(e)
        }
    }

    suspend fun getProduct(id: Int): AsyncResult<Product> {
        return try {
            AsyncResult.Success(httpClient.get("$PRODUCT/$id").body<Product>())
        } catch (e: Exception) {
            AsyncResult.Error(e)
        }
    }

    suspend fun addProduct(product: Product): AsyncResult<Product> {
        return try {
            AsyncResult.Success(httpClient.post(PRODUCT) {
                setBody(product)
            }.body<Product>())
        } catch (e: Exception) {
            AsyncResult.Error(e)
        }
    }
}