package data.local.settings

data class AppPreferences(
    val isDarkMode: Boolean, val token: String, val refreshToken: String
) {
    companion object {
        const val IS_DARK_MODE = "is_dark_mode"
        const val TOKEN = "token"
        const val REFRESH_TOKEN = "refresh_token"
    }
}
