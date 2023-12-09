package navigations

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import screens.main.home.Home
import screens.main.message.Message
import screens.main.newPost.NewPost
import screens.main.profile.Profile
import screens.main.search.Search
import shared.UIComposable

class BottomNav(private val firstScreen: Tab = Home()) : Screen, UIComposable {
    @Composable
    override fun Content() {
        Render()
    }

    @Composable
    override fun Render() {
        TabNavigator(firstScreen) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    content = {
                        CurrentTab()
                    },
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colors.background,
                            modifier = Modifier.height(56.dp),
                        ) {
                            TabNavigationItem(Home())
                            TabNavigationItem(Search())
                            TabNavigationItem(NewPost())
                            TabNavigationItem(Message())
                            TabNavigationItem(Profile())
                        }
                    },
                )
            }
        }
    }

}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) { // extension function
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it, contentDescription = tab.options.title
                )
            }
        },
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.onSurface,
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
