package screens.auth

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.local.database.AppStateService
import data.remote.product.ProductService
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreenModel : ScreenModel, KoinComponent {
    private val productService by inject<ProductService>()
    private val appStateService by inject<AppStateService>()

    fun changeAppTheme() {
//        screenModelScope.launch {
//            val currentAppState = appStateService.getAppState()
//            appStateService.updateAppState(AppStateModel().apply {
//                isDarkMode = !currentAppState.stateIn(screenModelScope).value?.isDarkMode!!
//            })
//        }
    }

    fun getAppState() {
        screenModelScope.launch {
            println(appStateService.getAppState().stateIn(screenModelScope).value)
        }
    }
}