package org.edward.app.data.local

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveDarkTheme(isDark: Boolean)
    fun isDarkTheme(): Flow<Boolean>

    suspend fun saveAccessToken(token: String)
    fun getAccessToken(): Flow<String?>

    suspend fun saveRefreshToken(token: String)
    fun getRefreshToken(): Flow<String?>
}