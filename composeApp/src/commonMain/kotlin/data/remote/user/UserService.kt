package data.remote.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.ServiceResult

class UserService : KoinComponent {

    private val httpClient by inject<HttpClient>()

    companion object {
        const val USER = "users"
    }

    suspend fun getUsers(): ServiceResult {
        return try {
            ServiceResult.Loading(true)
            val response: List<User> = Json.decodeFromString(
                httpClient.get(USER).body()
            )
            ServiceResult.Success(response)
            ServiceResult.Loading(false)
        } catch (e: Exception) {
            ServiceResult.Error(e.message ?: "An error occurred")
            ServiceResult.Loading(false)
        }
    }

    suspend fun getUser(id: Int): ServiceResult {
        return try {
            ServiceResult.Loading(true)
            val response: User = httpClient.get("$USER/$id").body()
            ServiceResult.Success(response)
            ServiceResult.Loading(false)
        } catch (e: Exception) {
            ServiceResult.Error(e.message ?: "An error occurred")
            ServiceResult.Loading(false)
        }
    }

    suspend fun addUser(user: User): ServiceResult {
        return try {
            ServiceResult.Loading(true)
            val response: User = httpClient.post(USER) {
                setBody(user)
            }.body()
            ServiceResult.Success(response)
            ServiceResult.Loading(false)
        } catch (e: Exception) {
            ServiceResult.Error(e.message ?: "An error occurred")
            ServiceResult.Loading(false)
        }
    }
}