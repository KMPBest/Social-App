package data.remote.user

import data.utils.AsyncResult

interface UserRepository {
    suspend fun getUsers(): AsyncResult<List<User>>
    suspend fun getUser(id: Int): AsyncResult<User>
    suspend fun addUser(user: User): AsyncResult<User>
}