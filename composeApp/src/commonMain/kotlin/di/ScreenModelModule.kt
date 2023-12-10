package di

import org.koin.dsl.module
import screens.auth.LoginScreenModel

val ScreenModelModule = module {
    factory { LoginScreenModel() }
}