import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import navigations.BottomNav

@Composable
fun MainNav() {
    Navigator(BottomNav()) {
        Scaffold(
            content = {
                CurrentScreen()
            }
        )
    }
}