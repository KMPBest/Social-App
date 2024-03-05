package di

import data.local.database.AppDatabaseRepository
import data.local.database.AppDatabaseRepositoryImpl
import data.local.settings.AppPreferencesRepository
import data.local.settings.AppPreferencesRepositoryImpl
import data.remote.product.ProductRepository
import data.remote.product.ProductRepositoryImpl
import data.remote.user.UserRepository
import data.remote.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AppDatabaseRepository> { AppDatabaseRepositoryImpl() }
    single<AppPreferencesRepository> { AppPreferencesRepositoryImpl() }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}