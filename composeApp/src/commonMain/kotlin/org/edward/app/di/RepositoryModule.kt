package org.edward.app.di

import org.edward.app.data.remote.product.ProductRepository
import org.edward.app.data.remote.product.ProductRepositoryImpl
import org.edward.app.data.remote.user.UserRepository
import org.edward.app.data.remote.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}