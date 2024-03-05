package data.local.settings

import com.russhwolf.settings.Settings

class AppPreferencesRepositoryImpl : AppPreferencesRepository {
    private val setting: Settings = Settings()

    override fun isDarkMode() = setting.getBoolean(AppPreferences.IS_DARK_MODE, false)

    override fun setDarkMode(isDarkMode: Boolean) =
        setting.putBoolean(AppPreferences.IS_DARK_MODE, isDarkMode)

    override fun getToken() = setting.getString(AppPreferences.TOKEN, "")

    override fun setToken(token: String) = setting.putString(AppPreferences.TOKEN, token)

    override fun getRefreshToken() = setting.getString(AppPreferences.REFRESH_TOKEN, "")

    override fun setRefreshToken(refreshToken: String) =
        setting.putString(AppPreferences.REFRESH_TOKEN, refreshToken)

    override fun getAppSetting() = AppPreferences(
        isDarkMode = isDarkMode(), token = getToken(), refreshToken = getRefreshToken()
    )

    override fun setAppSetting(appPreferences: AppPreferences) {
        setDarkMode(appPreferences.isDarkMode)
        setToken(appPreferences.token)
        setRefreshToken(appPreferences.refreshToken)
    }

    override fun clear() = setting.clear()

}