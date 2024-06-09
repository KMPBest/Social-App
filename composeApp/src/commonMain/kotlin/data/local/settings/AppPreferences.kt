package data.local.settings

import kotlinx.coroutines.flow.Flow

data class AppPreferences(
    val isDarkMode: Flow<Boolean>, val token: String, val refreshToken: String
) {
    companion object {
        const val IS_DARK_MODE = "is_dark_mode"
        const val TOKEN = "token"
        const val REFRESH_TOKEN = "refresh_token"
    }
}
