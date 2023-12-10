package di

import org.koin.core.context.startKoin

val appModule = listOf(networkModule, screenModelModule)

fun initKoin() = startKoin {
    modules(appModule)
}

