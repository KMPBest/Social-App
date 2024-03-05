package screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreen : Screen, KoinComponent {
    private val screenModel by inject<LoginScreenModel>()

    @Composable
    override fun Content() {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp).width(200.dp).clickable {
                screenModel.changeAppTheme()
                screenModel.getAppState()
            }) {
                Text("Login Screen")
            }
        }
    }
}