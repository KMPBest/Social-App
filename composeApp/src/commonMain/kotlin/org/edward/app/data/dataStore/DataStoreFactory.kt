package org.edward.app.data.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface DataStoreFactory {
    fun makeDataStore(): DataStore<Preferences>
    fun getDataStore(): DataStore<Preferences>
}