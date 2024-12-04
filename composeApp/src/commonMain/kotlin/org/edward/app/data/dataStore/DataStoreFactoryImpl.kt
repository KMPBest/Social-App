package org.edward.app.data.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.core.KoinApplication
import org.koin.dsl.module

internal class DataStoreFactoryImpl(
    private val preferenceBasePath: String,
) : DataStoreFactory {

    private lateinit var dataStore: DataStore<Preferences>

    companion object {
        private const val FILE_EXTENSION = ".preferences_pb"
    }

    override fun makeDataStore(): DataStore<Preferences> {
        dataStore = PreferenceDataStoreFactory.createWithPath {
            "$preferenceBasePath$FILE_EXTENSION".toPath()
        }
        return dataStore
    }

    override fun getDataStore(): DataStore<Preferences> = dataStore
}

fun KoinApplication.makePreferenceAreas(basePath: String) {
    val module = module {
        val facade = DataStoreFactoryImpl(basePath)
        single<DataStore<Preferences>> { facade.makeDataStore() }
    }
    koin.loadModules(listOf(module))
}