import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import data.local.settings.AppPreferencesRepository
import di.appModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        val appStateService = koinInject<AppPreferencesRepository>()

        println(appStateService.getAppSetting())

        MaterialTheme {
            AppTheme {
                MainNav()
            }
        }
    }
}