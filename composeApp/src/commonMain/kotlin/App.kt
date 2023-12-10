import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.appModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme {
            AppTheme {
                MainNav()
            }
        }
    }
}