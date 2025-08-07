package org.edward.app.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    require(
        value = context is Context,
        lazyMessage = { "Context must be of type android.content.Context" }
    )

    return AppSetting.getDataStore(producerPath = {
        context.filesDir.resolve(dataStoreFileName).absolutePath
    })
}