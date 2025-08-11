package org.edward.app.presentations.screens.auth.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.edward.app.data.local.DataStoreRepository
import org.koin.core.component.KoinComponent


class LoginScreenModel(
    private val dataStoreRepository: DataStoreRepository
) : ScreenModel, KoinComponent {
    data class LoginUiState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    )

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun login(onSuccess: () -> Unit) {
        screenModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            delay(1000)

            if (_uiState.value.email.isNotBlank() && _uiState.value.password.isNotBlank()) {
                dataStoreRepository.saveAccessToken("fake_access_token_123", ttl = 3600)
                dataStoreRepository.saveRefreshToken("fake_refresh_token_456", ttl = 7200)
                onSuccess()
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Invalid email or password"
                )
            }
        }
    }
}