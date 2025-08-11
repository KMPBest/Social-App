package org.edward.app.navigations

import cafe.adriel.voyager.navigator.Navigator
import org.edward.app.screens.auth.LoginScreen

sealed interface AppDestination {
    object Login : AppDestination
    object Home : AppDestination
}

fun Navigator.navigateTo(destination: AppDestination) {
    when (destination) {
        AppDestination.Login -> this.replace(LoginScreen())
        AppDestination.Home -> this.replace(BottomNav())
    }
}