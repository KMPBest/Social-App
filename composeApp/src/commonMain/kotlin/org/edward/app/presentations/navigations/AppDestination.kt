package org.edward.app.presentations.navigations

import cafe.adriel.voyager.navigator.Navigator
import org.edward.app.presentations.screens.auth.login.LoginScreen

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