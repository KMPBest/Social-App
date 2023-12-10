package di

import org.koin.dsl.module
import screens.auth.LoginScreenModel

val screenModelModule = module {
    factory { LoginScreenModel() }
}