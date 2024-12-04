package org.edward.app.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStoreReferences(context: Context): DataStore<Preferences> =
    createDataStore {
        context.filesDir.resolve(dataStoreFileName).absolutePath
    }
