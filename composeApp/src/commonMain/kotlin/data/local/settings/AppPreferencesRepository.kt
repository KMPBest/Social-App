package data.local.settings

interface AppPreferencesRepository {
    fun isDarkMode(): Boolean
    fun setDarkMode(isDarkMode: Boolean)
    fun getToken(): String
    fun setToken(token: String)
    fun getRefreshToken(): String
    fun setRefreshToken(refreshToken: String)
    fun getAppSetting(): AppPreferences
    fun setAppSetting(appPreferences: AppPreferences)
    fun clear()
}