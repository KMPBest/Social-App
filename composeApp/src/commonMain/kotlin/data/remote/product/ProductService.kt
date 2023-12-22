package data.remote.product

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.ServiceResult

class ProductService : KoinComponent {
    private val httpClient by inject<HttpClient>()

    companion object {
        const val PRODUCT = "products"
    }

    suspend fun getProducts(): ServiceResult {
        return try {
            ServiceResult.Loading(true)
            val response: List<Product> = httpClient.get(PRODUCT).body()
            ServiceResult.Success(response)
            ServiceResult.Loading(false)
        } catch (e: Exception) {
            ServiceResult.Error(e.message ?: "An error occurred")
            ServiceResult.Loading(false)
        }
    }

    suspend fun getProduct(id: Int): ServiceResult {
        return try {
            ServiceResult.Loading(true)
            val response: Product = httpClient.get("$PRODUCT/$id").body()
            ServiceResult.Success(response)
            ServiceResult.Loading(false)
        } catch (e: Exception) {
            ServiceResult.Error(e.message ?: "An error occurred")
            ServiceResult.Loading(false)
        }
    }

    suspend fun addProduct(product: Product): ServiceResult {
        return try {
            ServiceResult.Loading(true)
            val response: Product = httpClient.post(PRODUCT) {
                setBody(product)
            }.body()
            ServiceResult.Success(response)
            ServiceResult.Loading(false)
        } catch (e: Exception) {
            ServiceResult.Error(e.message ?: "An error occurred")
            ServiceResult.Loading(false)
        }
    }
}