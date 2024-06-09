import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import data.local.settings.AppPreferencesRepository
import di.appModule
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    val appPreferences = koinInject<AppPreferencesRepository>()

    val isDarkMode = MutableStateFlow(false)

    KoinApplication(application = {
        modules(appModule)
    }) {

        MainScope().launch {
            appPreferences.isDarkMode().collect {
                isDarkMode.value = it
            }
        }

        AppTheme(Color.Blue, isDarkMode.value) {
            MainNav()
        }
    }
}