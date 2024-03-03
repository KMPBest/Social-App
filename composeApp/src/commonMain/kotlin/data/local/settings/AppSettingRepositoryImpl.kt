package data.local.settings

import com.russhwolf.settings.Settings
import domain.models.AppSetting
import domain.repositories.AppSettingRepository

class AppSettingRepositoryImpl : AppSettingRepository {
    private val setting: Settings = TODO()

    override fun isDarkMode() = setting.getBoolean(AppSetting.IS_DARK_MODE, false)

    override fun setDarkMode(isDarkMode: Boolean) =
        setting.putBoolean(AppSetting.IS_DARK_MODE, isDarkMode)

    override fun getToken() = setting.getString(AppSetting.TOKEN, "")

    override fun setToken(token: String) = setting.putString(AppSetting.TOKEN, token)

    override fun getRefreshToken() = setting.getString(AppSetting.REFRESH_TOKEN, "")

    override fun setRefreshToken(refreshToken: String) =
        setting.putString(AppSetting.REFRESH_TOKEN, refreshToken)

    override fun getAppSetting() = AppSetting(
        isDarkMode = isDarkMode(), token = getToken(), refreshToken = getRefreshToken()
    )

    override fun setAppSetting(appSetting: AppSetting) {
        setDarkMode(appSetting.isDarkMode)
        setToken(appSetting.token)
        setRefreshToken(appSetting.refreshToken)
    }

    override fun clear() = setting.clear()

}