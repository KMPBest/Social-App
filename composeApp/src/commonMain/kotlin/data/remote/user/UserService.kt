package data.remote.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.AsyncResult

class UserService : KoinComponent {

    private val httpClient by inject<HttpClient>()

    companion object {
        const val USER = "users"
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun getUsers(): AsyncResult<List<User>> {
        return try {
            AsyncResult.Success(httpClient.get(USER).body<List<User>>())
        } catch (e: Exception) {
            AsyncResult.Error(e)
        }
    }

    suspend fun getUser(id: Int): AsyncResult<User> {
        return try {
            AsyncResult.Success(httpClient.get("$USER/$id").body<User>())
        } catch (e: Exception) {
            AsyncResult.Error(e)
        }
    }

    suspend fun addUser(user: User): AsyncResult<User> {
        return try {
            AsyncResult.Success(httpClient.post(USER) {
                setBody(user)
            }.body<User>())
        } catch (e: Exception) {
            AsyncResult.Error(e)
        }
    }
}