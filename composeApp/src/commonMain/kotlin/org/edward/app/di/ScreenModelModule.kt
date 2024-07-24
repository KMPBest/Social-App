package org.edward.app.di

import org.koin.dsl.module
import org.edward.app.screens.auth.LoginScreenModel
import org.edward.app.screens.main.home.HomeScreenModel
import org.edward.app.screens.main.profile.ProfileScreenModel

val screenModelModule = module {
    factory { LoginScreenModel() }
    factory { HomeScreenModel() }
    factory { ProfileScreenModel() }
}