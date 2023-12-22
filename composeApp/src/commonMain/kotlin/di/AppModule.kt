package di

import org.koin.core.context.startKoin

val appModule = listOf(networkModule, screenModelModule, serviceModule)

fun initKoin() = startKoin {
    modules(appModule)
}

