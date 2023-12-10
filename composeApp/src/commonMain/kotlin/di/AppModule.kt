package di

import org.koin.core.context.startKoin

val appModule = listOf(NetworkModule, ScreenModelModule)

fun initKoin() = startKoin {
    modules(appModule)
}

