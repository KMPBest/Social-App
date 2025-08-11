package org.edward.app.screens.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import org.edward.app.screens.main.home.HomeScreen

class ProfileScreen : Tab {

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            icon = rememberVectorPainter(Icons.Default.Person),
            title = "Profile",
        )

    @Composable
    override fun Content() {
        val greetingText by remember { mutableStateOf("org.edward.app.screens.main.profile") }
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

