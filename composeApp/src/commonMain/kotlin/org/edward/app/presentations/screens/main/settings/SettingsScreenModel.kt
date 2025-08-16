package org.edward.app.presentations.screens.main.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.edward.app.data.local.DataStoreRepository
import org.koin.core.component.KoinComponent

class SettingsScreenModel(private val dataStoreRepository: DataStoreRepository) :
    ScreenModel, KoinComponent {

    data class SettingsState(
        var isDarkTheme: Boolean = false
    )

    private val _uiState = MutableStateFlow(SettingsState())

    val uiState: StateFlow<SettingsState> = _uiState

    fun loadData() {
        screenModelScope.launch {
            val isDarkState = dataStoreRepository.isDarkTheme().firstOrNull() ?: false
            _uiState.value = _uiState.value.copy(isDarkTheme = !isDarkState);
        }
    }

    fun changeTheme() {
        screenModelScope.launch {
            val isDarkState = dataStoreRepository.isDarkTheme().firstOrNull() ?: false

            dataStoreRepository.saveDarkTheme(!isDarkState)
            _uiState.value = _uiState.value.copy(isDarkTheme = !isDarkState);
        }
    }
}