package org.edward.app.di

import org.edward.app.data.local.DataStoreRepository
import org.edward.app.data.local.DataStoreRepositoryImpl
import org.edward.app.shared.createDataStore
import org.koin.dsl.module

fun appModule(context: Any? = null) = listOf(
    networkModule, screenModelModule, repositoryModule, module {
        single<DataStoreRepository> {
            DataStoreRepositoryImpl(createDataStore(context))
        }
    }
)