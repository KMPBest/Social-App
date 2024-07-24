package org.edward.app.screens.auth

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class LoginScreenModel : ScreenModel, KoinComponent {

    fun changeAppTheme() {
        screenModelScope.launch {

        }
    }

}