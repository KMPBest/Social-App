package di

import data.local.database.AppDatabaseRepositoryImpl
import data.local.settings.AppSettingRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { AppDatabaseRepositoryImpl() }
    single { AppSettingRepositoryImpl() }
}