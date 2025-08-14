package org.edward.app.presentations.screens.chat

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.random.Random
import kotlin.time.ExperimentalTime

class ChatScreenModel : ScreenModel, KoinComponent {

    private val openAI = OpenAI("")

    data class ClientChatMessage @OptIn(ExperimentalTime::class) constructor(
        val text: String,
        val isUser: Boolean,
        val id: Long = Random.nextLong(0, Long.MAX_VALUE)
    )

    data class ChatUiState(
        val currentInput: String = "",
        val isThinking: Boolean = false,
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

            try {
                val reply = ask(tempText)

                _uiState.value = _uiState.value.copy(
                    messages = _uiState.value.messages + ClientChatMessage(reply, false),
                    isThinking = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    messages = _uiState.value.messages + ClientChatMessage(
                        "Error: ${e.message}",
                        false
                    ),
                    isThinking = false
                )
            }
        }
    }

    suspend fun ask(messages: String): String {
        val chatMessages = buildList {
            add(
                ChatMessage(
                    ChatRole.System,
                    "Bạn là người tư vấn giải đáp về bảo hiểm, hãy trả lời chi tiết nhất có thể"
                )
            )
            add(
                ChatMessage(
                    ChatRole.User,
                    messages
                )
            )
        }

        val completion = openAI.chatCompletion(
            request = ChatCompletionRequest(
                model = ModelId(""),
                messages = chatMessages,
            )
        )

        Napier.i(completion.toString())

        return completion.choices.firstOrNull()?.message?.content ?: "No response."
    }
}