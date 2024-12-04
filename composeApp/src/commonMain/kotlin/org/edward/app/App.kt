package org.edward.app

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import multiplatform_app.composeapp.generated.resources.IndieFlower_Regular
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.cyclone
import multiplatform_app.composeapp.generated.resources.ic_cyclone
import multiplatform_app.composeapp.generated.resources.ic_dark_mode
import multiplatform_app.composeapp.generated.resources.ic_light_mode
import multiplatform_app.composeapp.generated.resources.ic_rotate_right
import multiplatform_app.composeapp.generated.resources.open_github
import multiplatform_app.composeapp.generated.resources.run
import multiplatform_app.composeapp.generated.resources.stop
import multiplatform_app.composeapp.generated.resources.theme
import org.edward.app.data.dataStore.DataStoreKeys
import org.edward.app.data.dataStore.dataStoreFileName
import org.edward.app.data.dataStore.makePreferenceAreas
import org.edward.app.di.appModule
import org.edward.app.theme.AppTheme
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.KoinApplication
import org.koin.compose.getKoin

internal expect fun openUrl(url: String?)

@Composable
internal fun App() {

    val scope = rememberCoroutineScope()

    KoinApplication(application = {
        makePreferenceAreas(dataStoreFileName)
        modules(appModule)
    }) {

        val preferences = getKoin().get<DataStore<Preferences>>()

        val isDarkState by preferences.data.map {
            it[DataStoreKeys.DARK_THEME] ?: false
        }.collectAsState(isSystemInDarkTheme())

        AppTheme(isDarkState) {
            Column(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.cyclone),
                    fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
                    style = MaterialTheme.typography.displayLarge
                )

                var isAnimate by remember { mutableStateOf(false) }
                val transition = rememberInfiniteTransition()
                val rotate by transition.animateFloat(
                    initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
                        animation = tween(1000, easing = LinearEasing)
                    )
                )

                Image(
                    modifier = Modifier.size(250.dp).padding(16.dp)
                        .run { if (isAnimate) rotate(rotate) else this },
                    imageVector = vectorResource(Res.drawable.ic_cyclone),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    contentDescription = null
                )

                ElevatedButton(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp), onClick = { isAnimate = !isAnimate }, content = {
                    Icon(
                        vectorResource(Res.drawable.ic_rotate_right), contentDescription = null
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        stringResource(if (isAnimate) Res.string.stop else Res.string.run)
                    )
                })

                val icon = remember(isDarkState) {
                    if (isDarkState) Res.drawable.ic_light_mode
                    else Res.drawable.ic_dark_mode
                }

                ElevatedButton(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp), onClick = {
                    scope.launch {
                        preferences.edit { it[DataStoreKeys.DARK_THEME] = !isDarkState }
                    }
                }, content = {
                    Icon(vectorResource(icon), contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(Res.string.theme))
                })

                TextButton(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        .widthIn(min = 200.dp),
                    onClick = { openUrl("https://github.com/terrakok") },
                ) {
                    Text(stringResource(Res.string.open_github))
                }
            }
        }
    }
}



