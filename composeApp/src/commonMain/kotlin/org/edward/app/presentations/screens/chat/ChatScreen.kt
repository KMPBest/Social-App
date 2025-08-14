package org.edward.app.presentations.screens.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import kotlinx.coroutines.flow.first
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.component.KoinComponent

class ChatScreen : Screen, KoinComponent {

    @Composable
    @Preview
    override fun Content() {
        val viewModel = koinScreenModel<ChatScreenModel>()
        val uiState by viewModel.uiState.collectAsState()
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        var isFocused by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        ) {
            val listState = rememberLazyListState()

            LaunchedEffect(uiState.messages.size, uiState.isThinking) {
                snapshotFlow { listState.layoutInfo.totalItemsCount }.first { it > 0 }
                listState.animateScrollToItem(0)
            }

            LazyColumn(
                modifier = Modifier.weight(1f).padding(8.dp),
                reverseLayout = true,
                state = listState,
            ) {
                if (uiState.isThinking) {
                    item {
                        ChatBubble(ChatScreenModel.ClientChatMessage("Thinking...", isUser = false))
                        Spacer(Modifier.height(4.dp))
                    }
                }
                items(uiState.messages.reversed(), key = { it.id }) { message ->
                    ChatBubble(message)
                    Spacer(Modifier.height(4.dp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Transparent, CircleShape),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = uiState.currentInput,
                    onValueChange = viewModel::onInputChange,
                    placeholder = { Text("Type a message...") },
                    singleLine = true,
                    shape = CircleShape,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Ascii,
                        imeAction = ImeAction.Send,
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (uiState.currentInput.isNotBlank() && !uiState.isThinking) {
                                viewModel.sendMessage()
                            }
                        },
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .onFocusChanged { isFocused = it.isFocused },
                )

                AnimatedVisibility(visible = isFocused && uiState.currentInput.isNotBlank()) {
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = viewModel::sendMessage,
                        enabled = !uiState.isThinking,
                        modifier = Modifier.background(Color.Transparent, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.Send,
                            contentDescription = "Send",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ChatBubble(message: ChatScreenModel.ClientChatMessage) {
        val bubbleColor = if (message.isUser) Color(0xFFDCF8C6) else Color(0xFFFFFFFF)
        val bubbleShape = if (message.isUser) {
            RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 0.dp,
                bottomEnd = 12.dp,
                bottomStart = 12.dp
            )
        } else {
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 12.dp,
                bottomEnd = 12.dp,
                bottomStart = 12.dp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .background(bubbleColor, bubbleShape)
                    .shadow(1.dp, bubbleShape)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = message.text,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )
            }
        }
    }
}