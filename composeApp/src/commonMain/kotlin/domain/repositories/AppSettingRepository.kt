package domain.repositories

import domain.models.AppSetting

interface AppSettingRepository {
    fun isDarkMode(): Boolean
    fun setDarkMode(isDarkMode: Boolean)
    fun getToken(): String
    fun setToken(token: String)
    fun getRefreshToken(): String
    fun setRefreshToken(refreshToken: String)
    fun getAppSetting(): AppSetting
    fun setAppSetting(appSetting: AppSetting)
    fun clear()
}