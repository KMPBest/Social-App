package di

import org.koin.core.context.startKoin

val appModule = listOf(networkModule, screenModelModule, serviceModule, repositoryModule)

fun initKoin() = startKoin {
    modules(appModule)
}

