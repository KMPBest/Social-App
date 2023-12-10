package screens.auth

import cafe.adriel.voyager.core.model.ScreenModel

class LoginScreenModel : ScreenModel {
    override fun onDispose() {
        println("On dispose LoginScreenModel")
        super.onDispose()
    }

    fun testLog() {
        println("I'm Edward")
    }
}