package org.edward.app.presentations.screens.main.settings

import cafe.adriel.voyager.core.model.ScreenModel
import org.edward.app.data.local.DataStoreRepository
import org.koin.core.component.KoinComponent

class SettingsScreenModel(private val dataStoreRepository: DataStoreRepository) :
    ScreenModel, KoinComponent