package di

import data.local.AppStateRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppStateRepository() }
}