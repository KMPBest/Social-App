import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.NetworkModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(NetworkModule)
    }) {
        MaterialTheme {
            AppTheme {
                MainNav()
            }
        }
    }
}