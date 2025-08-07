package org.edward.app.screens.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Home
import org.koin.core.component.KoinComponent

class HomeScreen : Tab, KoinComponent {

    override val options: TabOptions
        @Composable get() = TabOptions(
            index = 0u,
            icon = rememberVectorPainter(FontAwesomeIcons.Solid.Home),
            title = "Home",
        )

    @Composable
    override fun Content() {
        val greetingText by remember { mutableStateOf("org.edward.app.screens.main.home") }
        LocalNavigator.currentOrThrow
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {

            }) {
                Text("$greetingText!")
            }
        }
    }

}