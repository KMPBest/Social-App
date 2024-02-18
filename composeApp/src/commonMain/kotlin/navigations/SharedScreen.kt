package navigations

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.screenModule
import screens.auth.LoginScreen

sealed class SharedScreen : ScreenProvider {
    data object Login : SharedScreen()

    data object BottomNav : SharedScreen()
}

val featureScreenModule = screenModule {
    register<SharedScreen.BottomNav> {
        BottomNav()
    }

    register<SharedScreen.Login> {
        LoginScreen()
    }
}
