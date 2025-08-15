package org.edward.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import io.github.aakira.napier.Napier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.edward.app.data.local.DataStoreRepository
import org.edward.app.di.appModule
import org.edward.app.presentations.navigations.BottomNav
import org.edward.app.presentations.screens.components.KeyboardAwareContainer
import org.edward.app.presentations.theme.AppTheme
import org.edward.app.shared.initLogger
import org.koin.compose.KoinApplication
import org.koin.mp.KoinPlatform.getKoin
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun App(context: Any? = null) {
    initLogger()
    KoinApplication(application = { modules(appModule(context)) }) {

        val dataStoreRepository: DataStoreRepository = getKoin().get<DataStoreRepository>()
        val isDarkState by dataStoreRepository.isDarkTheme().collectAsState(initial = false)

        var entry: Screen = BottomNav()
        var isLoading by remember { mutableStateOf(true) }

        val lifecycleOwner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    coroutineScope.launch {
                        checkTokenValidity(dataStoreRepository).also {
                            if (it) {
                                entry = BottomNav()
                            }
                            isLoading = false
                        }
                    }
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
        }

        AppTheme(isDarkState) {
            KeyboardAwareContainer(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing),
                content = {
                    if (isLoading) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            LoadingIndicator()
                        }
                    } else {
                        Navigator(entry)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
private suspend fun checkTokenValidity(
    dataStoreRepository: DataStoreRepository
): Boolean {
    Napier.i { "Checking token validity..." }
    val tokenData = dataStoreRepository.getTokenData().firstOrNull()

    if (tokenData == null) {
        Napier.w("Token data is null, cannot check validity.")
        return false
    }

    if (tokenData.accessTokenExpiry <= Clock.System.now().epochSeconds && tokenData.refreshTokenExpiry <= Clock.System.now().epochSeconds) {
        Napier.w("Access token and refresh token are both expired.")
        return false
    }

    if (Clock.System.now().epochSeconds < tokenData.refreshTokenExpiry) {
        Napier.i { "Access token is expired, but refresh token is valid. Refreshing access token..." }
        delay(5000)
        dataStoreRepository.saveAccessToken("new_access_token_123", 3600)
        dataStoreRepository.saveRefreshToken("new_refresh_token_456", 7200)
        Napier.i { "Access token refreshed successfully." }
    }

    return true
}


