package data.local.settings

import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {
    suspend fun isDarkMode(): Flow<Boolean>
    suspend fun setDarkMode(isDarkMode: Boolean)
    suspend fun getToken(): String
    suspend fun setToken(token: String)
    suspend fun getRefreshToken(): String
    suspend fun setRefreshToken(refreshToken: String)
    suspend fun getAppSetting(): AppPreferences
    suspend fun clear()
}