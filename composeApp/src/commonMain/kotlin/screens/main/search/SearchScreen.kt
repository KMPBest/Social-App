package screens.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import screens.main.home.HomeScreen
import shared.UIComposable

class SearchScreen : Tab, UIComposable {

    override val options: TabOptions
        @Composable get() = TabOptions(
            index = 2u,
            icon = rememberVectorPainter(Icons.Outlined.Search),
            title = "Search",
        )

    @Composable
    override fun Content() {
        Render()
    }

    @Composable
    override fun Render() {
        val greetingText by remember { mutableStateOf("screens.main.search") }
        val navigator = LocalNavigator.currentOrThrow

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                navigator.push(HomeScreen())
            }) {
                Text("$greetingText!")
            }
        }
    }
}