package screens.auth

import cafe.adriel.voyager.core.model.ScreenModel
import data.remote.user.UserService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreenModel : ScreenModel, KoinComponent {
    private val userService by inject<UserService>()

    override fun onDispose() {
        println("On dispose LoginScreenModel")
        super.onDispose()
    }

    suspend fun testApi() {
        userService.getUsers()
    }
}