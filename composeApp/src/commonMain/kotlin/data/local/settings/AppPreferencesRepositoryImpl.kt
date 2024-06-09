package data.local.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings

@OptIn(ExperimentalSettingsApi::class)
expect val setting: FlowSettings

class AppPreferencesRepositoryImpl : AppPreferencesRepository {

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun isDarkMode() =
        setting.getBooleanFlow(AppPreferences.IS_DARK_MODE, false)

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun setDarkMode(isDarkMode: Boolean) =
        setting.putBoolean(AppPreferences.IS_DARK_MODE, isDarkMode)

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun getToken() = setting.getString(AppPreferences.TOKEN, "")

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun setToken(token: String) = setting.putString(AppPreferences.TOKEN, token)

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun getRefreshToken() = setting.getString(AppPreferences.REFRESH_TOKEN, "")

    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun setRefreshToken(refreshToken: String) =
        setting.putString(AppPreferences.REFRESH_TOKEN, refreshToken)

    override suspend fun getAppSetting() = AppPreferences(
        isDarkMode = isDarkMode(), token = getToken(), refreshToken = getRefreshToken()
    )
    
    @OptIn(ExperimentalSettingsApi::class)
    override suspend fun clear() = setting.clear()

}