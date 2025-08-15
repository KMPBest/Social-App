package org.edward.app.presentations.screens.chat

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.edward.app.data.local.DataStoreRepository
import org.edward.app.data.remote.openai.OpenAIRepository
import org.edward.app.data.utils.AsyncResult
import org.koin.core.component.KoinComponent
import kotlin.random.Random
import kotlin.time.ExperimentalTime

class ChatScreenModel(
    private val openAIRepository: OpenAIRepository,
    private val dataStoreRepository: DataStoreRepository
) : ScreenModel, KoinComponent {

    data class ClientChatMessage @OptIn(ExperimentalTime::class) constructor(
        val text: String,
        val isUser: Boolean,
        val id: Long = Random.nextLong(0, Long.MAX_VALUE)
    )

    data class ChatUiState(
        val currentInput: String = "",
        val isThinking: Boolean = false,
        val error: String? = null,
        var messages: List<ClientChatMessage> = emptyList()
    )

    private val _uiState = MutableStateFlow(ChatUiState())

    val uiState: StateFlow<ChatUiState> = _uiState

    fun onInputChange(newInput: String) {
        _uiState.value = _uiState.value.copy(currentInput = newInput)
    }

    fun sendMessage() {
        screenModelScope.launch {
            if (_uiState.value.currentInput.isBlank()) return@launch

            val tempText = _uiState.value.currentInput

            _uiState.value = _uiState.value.copy(
                messages = _uiState.value.messages + ClientChatMessage(
                    text = _uiState.value.currentInput,
                    isUser = true
                ),
                currentInput = "",
                isThinking = true
            )

            val reply = openAIRepository.ask(tempText)

            when (reply) {
                is AsyncResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        messages = _uiState.value.messages + ClientChatMessage(
                            text = reply.data,
                            isUser = false
                        ),
                        isThinking = false
                    )
                }

                is AsyncResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        error = reply.displayMessage ?: "An error occurred",
                        isThinking = false
                    )
                }
            }

        }
    }

    fun changeTheme() {
        screenModelScope.launch {
            val isDarkState = dataStoreRepository.isDarkTheme().firstOrNull() ?: false

            dataStoreRepository.saveDarkTheme(!isDarkState)
        }
    }

}