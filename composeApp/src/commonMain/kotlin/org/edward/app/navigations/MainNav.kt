package org.edward.app.navigations

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.edward.app.screens.auth.LoginScreen

@Composable
fun MainNav() {
    Navigator(LoginScreen()) {
        Scaffold(content = {
            CurrentScreen()
        })
    }
}