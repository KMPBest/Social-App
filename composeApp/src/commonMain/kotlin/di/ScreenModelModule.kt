package di

import AppScreenModel
import org.koin.dsl.module
import screens.auth.LoginScreenModel
import screens.main.home.HomeScreenModel
import screens.main.profile.ProfileScreenModel

val screenModelModule = module {
    factory { AppScreenModel() }
    factory { LoginScreenModel() }
    factory { HomeScreenModel() }
    factory { ProfileScreenModel() }
}