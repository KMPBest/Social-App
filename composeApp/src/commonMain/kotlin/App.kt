import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import di.appModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        val appStateService = koinInject<AppScreenModel>()

        appStateService.logAppSetting()

        MaterialTheme {
            AppTheme {
                MainNav()
            }
        }
    }
}