package screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import navigations.SharedScreen
import shared.UIComposable

class LoginScreen : Screen, UIComposable {

    @Composable
    override fun Content() {
        Render()
    }

    @Composable
    override fun Render() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomNav = rememberScreen(SharedScreen.BottomNav)

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(Modifier.fillMaxWidth().clickable {
                navigator.push(bottomNav)
            }) {
                Text("Login Screen")
            }
        }

    }
}