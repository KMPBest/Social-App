package org.edward.app.presentations.navigations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.edward.app.presentations.screens.main.home.HomeScreen
import org.edward.app.presentations.screens.main.profile.ProfileScreen

class BottomNav(private val firstScreen: Tab = HomeScreen()) : Screen {
    @Composable
    override fun Content() {
        TabNavigator(firstScreen) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.shadow(
                                elevation = 16.dp,
                                shape = MaterialTheme.shapes.large
                            )
                        ) {
                            TabNavigationItem(firstScreen)
                            TabNavigationItem(ProfileScreen())
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        CurrentTab()
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it, contentDescription = tab.options.title
                )
            }
        },
        label = { Text(tab.options.title) },
        alwaysShowLabel = false,
        modifier = Modifier.drawBehind {
            if (tabNavigator.current == tab) {
                val strokeWidth = 2.dp.toPx()
                val y = strokeWidth / 2
                drawLine(
                    color = Color.Red,
                    start = Offset(32f, y),
                    end = Offset(size.width - 32f, y),
                    strokeWidth = strokeWidth
                )
            }
        })

}
