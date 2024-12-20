package org.edward.app.data.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import okio.Path.Companion.toPath

fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })

internal const val dataStoreFileName = "dice"

object DataStoreKeys {
    val DARK_THEME = booleanPreferencesKey("dark_theme")
    val ACCESS_TOKEN = booleanPreferencesKey("access_token")
    val REFRESH_TOKEN = booleanPreferencesKey("refresh_token")
}


class MultiplatformSettings {
    val observableSettings: ObservableSettings = Settings()
}