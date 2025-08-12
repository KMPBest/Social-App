package org.edward.app.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {
    companion object Companion {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val ACCESS_TOKEN_EXPIRE_TIME = longPreferencesKey("access_token_expire_time")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val REFRESH_TOKEN_EXPIRE_TIME = longPreferencesKey("refresh_token_expire_time")
    }

    override suspend fun saveDarkTheme(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME] = isDark
        }
    }

    override fun isDarkTheme(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[DARK_THEME] ?: false
        }
    }

    override suspend fun saveAccessToken(token: String, ttl: Long) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token
            preferences[ACCESS_TOKEN_EXPIRE_TIME] = Clock.System.now().epochSeconds + ttl
        }
    }

    override fun getAccessToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            val token = preferences[ACCESS_TOKEN]
            val expiry = preferences[ACCESS_TOKEN_EXPIRE_TIME] ?: 0L
            if (token != null && Clock.System.now().epochSeconds < expiry) {
                token
            } else {
                null
            }
        }
    }

    override suspend fun saveRefreshToken(token: String, ttl: Long) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = token
            preferences[REFRESH_TOKEN_EXPIRE_TIME] = Clock.System.now().epochSeconds + ttl
        }
    }

    override fun getRefreshToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            val token = preferences[REFRESH_TOKEN]
            val expiry = preferences[REFRESH_TOKEN_EXPIRE_TIME] ?: 0L
            if (token != null && Clock.System.now().epochSeconds < expiry) {
                token
            } else {
                null
            }
        }
    }

    override fun getTokenData(): Flow<DataStoreRepository.Companion.TokenData> {
        return dataStore.data.map { preferences ->
            DataStoreRepository.Companion.TokenData(
                accessToken = preferences[ACCESS_TOKEN],
                accessTokenExpiry = preferences[ACCESS_TOKEN_EXPIRE_TIME] ?: 0L,
                refreshToken = preferences[REFRESH_TOKEN],
                refreshTokenExpiry = preferences[REFRESH_TOKEN_EXPIRE_TIME] ?: 0L
            )
        }
    }

}