package screens.auth

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.local.settings.AppPreferencesRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreenModel : ScreenModel, KoinComponent {
    private val appPreferences by inject<AppPreferencesRepository>()

    fun changeAppTheme() {
        screenModelScope.launch {
            appPreferences.isDarkMode().collect {
                appPreferences.setDarkMode(!it)
            }
        }
    }

}