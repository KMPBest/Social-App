import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import di.appModule
import navigations.featureScreenModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme {
            AppTheme {
                ScreenRegistry {
                    featureScreenModule()
                }
                MainNav()
            }
        }
    }
}