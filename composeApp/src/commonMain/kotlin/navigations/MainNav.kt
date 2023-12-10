import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import screens.auth.LoginScreen

@Composable
fun MainNav() {
    Navigator(LoginScreen()) {
        Scaffold(
            content = {
                CurrentScreen()
            }
        )
    }
}