package org.edward.app.di

import org.edward.app.screens.auth.LoginScreenModel
import org.edward.app.screens.main.home.HomeScreenModel
import org.edward.app.screens.main.profile.ProfileScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory { LoginScreenModel(get()) }
    factory { HomeScreenModel() }
    factory { ProfileScreenModel() }
}