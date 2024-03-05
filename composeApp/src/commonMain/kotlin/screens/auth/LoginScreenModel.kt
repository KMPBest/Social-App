package screens.auth

import cafe.adriel.voyager.core.model.ScreenModel
import data.remote.product.ProductRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreenModel : ScreenModel, KoinComponent {
    private val productService by inject<ProductRepository>()

    fun changeAppTheme() {
    }

    fun getAppState() {
    }
}