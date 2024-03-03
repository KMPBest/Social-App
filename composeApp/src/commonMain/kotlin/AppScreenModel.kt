import cafe.adriel.voyager.core.model.ScreenModel
import data.local.settings.AppSettingService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppScreenModel : ScreenModel, KoinComponent {
    private val appSettingService by inject<AppSettingService>()

    fun logAppSetting() {
        println("isDarkMode: ${appSettingService.isDarkMode()}")
        println("token: ${appSettingService.getToken()}")
        println("refreshToken: ${appSettingService.getRefreshToken()}")
    }
}