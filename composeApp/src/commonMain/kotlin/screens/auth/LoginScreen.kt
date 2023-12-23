package screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import shared.UIComposable

class LoginScreen : Screen, UIComposable {

    @Composable
    override fun Content() {
        Render()
    }

    @Composable
    override fun Render() {
        val greetingText by remember { mutableStateOf("screens.auth.Login") }
        val loginScreenModel = getScreenModel<LoginScreenModel>()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                loginScreenModel.testApi()
            }) {
                Text("$greetingText!")
            }
        }

    }
}